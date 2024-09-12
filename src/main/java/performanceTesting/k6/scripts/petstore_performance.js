import http from 'k6/http';
import { check, sleep } from 'k6';

// Load Test Configuration
export const options = {
    stages: [
        { duration: '30s', target: 10 }, // Ramp-up to 10 users
        { duration: '1m', target: 20 },  // Ramp-up to 20 users
        { duration: '2m', target: 50 },  // Sustained traffic of 50 users
        { duration: '1m', target: 0 },   // Ramp-down
    ],
};

const BASE_URL = 'https://petstore.swagger.io/v3';

// Define payload for POST and PUT requests
const petPayload = JSON.stringify({
    id: 1001,
    name: "doggie",
    category: { id: 1, name: "Dogs" },
    photoUrls: ["string"],
    tags: [{ id: 1, name: "cute" }],
    status: "available"
});

// 1. Add a new pet
export default function () {
    let res = http.post(`${BASE_URL}/pet`, petPayload, { headers: { 'Content-Type': 'application/json' } });
    check(res, {
        'status is 200': (r) => r.status === 200,
    });

    // 2. Update a pet
    let updateRes = http.put(`${BASE_URL}/pet`, petPayload, { headers: { 'Content-Type': 'application/json' } });
    check(updateRes, { 'status is 200': (r) => r.status === 200 });

    // 3. Retrieve a pet by ID
    let getRes = http.get(`${BASE_URL}/pet/1001`);
    check(getRes, { 'status is 200': (r) => r.status === 200 });

    // 4. Delete the pet
    let deleteRes = http.del(`${BASE_URL}/pet/1001`);
    check(deleteRes, { 'status is 200': (r) => r.status === 200 });

    sleep(1);
}

