import http from 'k6/http';
import { check, sleep } from 'k6';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";
import { textSummary } from "https://jslib.k6.io/k6-summary/0.0.1/index.js";
export const options = {
    vus: 50,
    duration: '30s',
};

export default function () {
    const url = 'https://petstore.swagger.io/v2/pet/findByStatus?status=available';
    const res = http.get(url);

    check(res, {
        'status is 200': (r) => r.status === 200,
        'response time < 200ms': (r) => r.timings.duration < 200,
        'response body is not empty': (r) => r.body.length > 0,
    });

    sleep(1);
}

export function handleSummary(data) {
    return {
        "get_pet_by_status.html": htmlReport(data),
        stdout: textSummary(data, { indent: " ", enableColors: true }),
    };
}
