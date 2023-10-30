import { API_URL} from "../../settings.js"

//Add id to this URL to get a single user
const URL = `${API_URL}/cars`

export function initFindEditCar(){
    const findCarButton = document.getElementById("btn-fetch-car") as HTMLButtonElement;
    const carIdInput = document.getElementById("car-id-input") as HTMLInputElement;
    
    findCarButton.addEventListener("click", function() {
        const carId = document.getElementById("car-id-input");


        fetch(API_URL + "/" + carId)
            .then(res => {
                if (!res.ok) {
                    throw new Error(`Car not found: ${res.status}`);
                }
                return res.json();
            })
            .then(car => {
                document.getElementById("brand").innerText = car.brand;
                document.getElementById("model").innerText = car.model;
                document.getElementById("price-pr-day").innerText = car.pricePrDay;
                document.getElementById("best-discount").innerText = car.bestDiscount;
            })
    })
}