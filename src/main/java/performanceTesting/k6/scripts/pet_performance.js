import http from 'k6/http';
import { check } from 'k6';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";
import { textSummary } from "https://jslib.k6.io/k6-summary/0.0.1/index.js";
function getRandomPetName() {
    const petList = ['Dog', 'Cat', 'Hamster']
    const randomIndex = Math.floor(Math.random() * petList.length);
    return petList[randomIndex];
}

export const options = {
    vus: 10,
    duration: '60s',
    thresholds: {
        http_req_duration: ['max<500']
    }
};

const headers = { 'Content-Type': 'application/json' };

export default function () {
    const payload = JSON.stringify(
        {
            name: getRandomPetName(),
            status: 'available',
        }
    );

    const res = http.post('https://petstore.swagger.io/v2/pet', payload, { headers });
    check(res, {
        'status was 200': (r) => r.status == 200,
        'response name same with payload': (r) => JSON.parse(r.body)['name'] == JSON.parse(payload)['name']
    });
}
export function handleSummary(data) {
    return {
        "get_user_login.html": htmlReport(data),
        stdout: textSummary(data, { indent: " ", enableColors: true }),
    };
}
