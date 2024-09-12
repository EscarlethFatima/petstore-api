import http from 'k6/http';
import { check, sleep } from 'k6';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";
import { textSummary } from "https://jslib.k6.io/k6-summary/0.0.1/index.js";

export const options = {
    vus: 100,
    duration: '30s',
};

export default function () {
    const url = 'https://petstore.swagger.io/v2/user/login?username=testuser&password=password123';
    const res = http.get(url);

    check(res, {
        'status is 200': (r) => r.status === 200,
        'response time < 200ms': (r) => r.timings.duration < 200,
        'logged in successfully': (r) => r.body.includes('logged in user session'),
    });

    sleep(1);
}

export function handleSummary(data) {
    return {
        "get_user_login.html": htmlReport(data),
        stdout: textSummary(data, { indent: " ", enableColors: true }),
    };
}

