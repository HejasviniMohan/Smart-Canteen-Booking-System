const API_BASE = "http://localhost:8080/api/auth";
const SLOT_API = "http://localhost:8080/api/slots";
const MENU_API = "http://localhost:8080/api/menu";

/* ==========================
   INIT
========================== */
function initApp() {
    showLogin();
}

/* ==========================
   UI TOGGLE
========================== */
function showRegister() {
    document.querySelector(".login-card").classList.add("d-none");
    document.getElementById("registerCard").classList.remove("d-none");
}

function showLogin() {
    document.querySelector(".login-card").classList.remove("d-none");
    document.getElementById("registerCard").classList.add("d-none");
}

/* ==========================
   DOM LOAD
========================== */
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("showRegisterBtn")?.addEventListener("click", showRegister);
    document.getElementById("backToLoginBtn")?.addEventListener("click", showLogin);
    document.getElementById("registerButton")?.addEventListener("click", registerUser);
    document.getElementById("loginButton")?.addEventListener("click", loginUser);
    document.getElementById("logoutButton")?.addEventListener("click", logoutUser);
});

/* ==========================
   REGISTER
========================== */
function registerUser() {
    const role = document.querySelector("input[name='registerRole']:checked").value.toUpperCase();
    const registerNo = document.getElementById("registerId").value.trim();
    const password = document.getElementById("registerPassword").value.trim();
    const confirmPassword = document.getElementById("registerConfirmPassword").value.trim();

    if (!registerNo || !password || !confirmPassword) {
        alert("Fill all fields");
        return;
    }

    if (password !== confirmPassword) {
        alert("Passwords do not match");
        return;
    }

    fetch(`${API_BASE}/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ role, registerNo, password })
    })
    .then(res => res.text())
    .then(msg => {
        alert(msg);
        if (msg.toLowerCase().includes("successful")) showLogin();
    });
}

/* ==========================
   LOGIN
========================== */
function loginUser() {
    const role = document.querySelector("input[name='role']:checked").value.toUpperCase();
    const loginId = document.getElementById("loginId").value.trim();
    const password = document.getElementById("loginPassword").value.trim();

    if (!loginId || !password) {
        alert("Enter ID and password");
        return;
    }

    fetch(`${API_BASE}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ role, registerNo: loginId, password })
    })
    .then(res => res.text())
    .then(msg => {
        alert(msg);
        if (msg.toLowerCase().includes("successful")) {
            document.getElementById("loginSection").classList.add("d-none");
            document.getElementById("mainHeader").classList.remove("d-none");
            document.getElementById("mainApp").classList.remove("d-none");

            loadMenu();   // ✅ MENU LOADS AFTER LOGIN
            loadSlots();
        }
    });
}

/* ==========================
   LOGOUT
========================== */
function logoutUser() {
    location.reload();
}

/* ==========================
   SLOT LOADING
========================== */
function loadSlots() {
    const slotGrid = document.getElementById("slotGrid");
    if (!slotGrid) return;

    fetch(SLOT_API)
        .then(res => res.json())
        .then(slots => {
            slotGrid.innerHTML = "";
            slots.forEach(slot => {
                const available = slot.capacity - slot.booked;

                const div = document.createElement("div");
                div.className = "erp-shell p-3";
                div.innerHTML = `
                    <h6>${slot.time}</h6>
                    <p>Available: ${available}</p>
                    <button class="btn btn-sm btn-primary"
                        ${available <= 0 ? "disabled" : ""}
                        onclick="bookSlot('${slot.id}')">
                        ${available <= 0 ? "Full" : "Book"}
                    </button>
                `;
                slotGrid.appendChild(div);
            });
        });
}

function bookSlot(id) {
    fetch(`${SLOT_API}/book/${id}`, { method: "POST" })
        .then(res => res.text())
        .then(msg => {
            alert(msg);
            loadSlots();
        });
}

/* ==========================
   MENU DISPLAY (FIXED)
========================== */
let allMenuItems = [];

function loadMenu() {
    fetch(MENU_API)
        .then(res => res.json())
        .then(data => {
            allMenuItems = data;
            renderMenu(data);
        });
}

function renderMenu(items) {
    const menuGrid = document.getElementById("menuGrid");
    if (!menuGrid) return;

    menuGrid.innerHTML = "";

    items.forEach(item => {
        const card = document.createElement("div");
        card.className = "col-md-4 col-lg-3 mb-4";

        card.innerHTML = `
            <div class="menu-card h-100">
                <div class="menu-card-body">
                    <h6 class="menu-title">${item.name}</h6>
                    <p class="menu-category">${item.category}</p>
                    <p class="menu-price">₹${item.price}</p>

                    ${item.popular ? `<span class="menu-badge">Popular</span>` : ""}
                </div>

                <button class="menu-btn" onclick="addToCart('${item.id}')">
                    Add to Cart
                </button>
            </div>
        `;

        menuGrid.appendChild(card);
    });
}

//Temp 
function addToCart(id) {
    alert("Item added to cart (ID: " + id + ")");
}

