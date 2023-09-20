document.addEventListener("DOMContentLoaded", function() {
    const fetchCarsButton = document.getElementById("btn-get-all");
    const carList = document.getElementById("carList");

    fetchCarsButton.addEventListener("click", function() {
        fetch("http://localhost:8080/api/cars")
            .then(res => {
                if (!res.ok) {
                    throw new Error(`HTTP error! Status: ${res.status}`);
                }
                return res.json();
            })
            .then(data => {
                const carListHTML = data.map(car => `
                <tr>
                    <td>${car.id}</td>
                    <td>${car.brand}</td>
                    <td>${car.model}</td>
                    <td>${car.pricePrDay}</td>
                    <td>${car.bestDiscount}</td>
                </tr>
            `).join('');

            carList.innerHTML = carListHTML;
            })
            .catch(e => {
                console.error("Error fetching cars", e);
            })
    })
})
