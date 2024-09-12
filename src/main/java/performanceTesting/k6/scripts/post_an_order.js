import http from 'k6/http';
import { check, sleep } from 'k6';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";
import { textSummary } from "https://jslib.k6.io/k6-summary/0.0.1/index.js";

export const options = {
    vus: 20,
    duration: '30s',
};

export default function () {
    const url = 'https://petstore.swagger.io/v3/store/order';
    const payload = JSON.stringify({
        id: Math.floor(Math.random() * 10000), // Random order ID
        petId: Math.floor(Math.random() * 10000), // Random pet ID
        quantity: 1,
        status: 'placed',
        complete: true,
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(url, payload, params);

    check(res, {
        'status is 200': (r) => r.status === 200,
        'response time < 300ms': (r) => r.timings.duration < 300,
    });

    sleep(1);
}

export function handleSummary(data) {
    return {
        "post_an_order.html": htmlReport(data),
        stdout: textSummary(data, { indent: " ", enableColors: true }),
    };
}

