import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 5,
    duration: '30s',
};

export default function () {
    const url = 'https://petstore.swagger.io/v2/user/createWithList';
    const payload = JSON.stringify([
        {
            id: Math.floor(Math.random() * 10000),
            username: `user${Math.floor(Math.random() * 10000)}`,
            firstName: 'John',
            lastName: 'Doe',
            email: 'john.doe@example.com',
            password: 'password123',
            phone: '1234567890',
            userStatus: 1,
        },
    ]);

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
        "post_lis_of_users.html": htmlReport(data),
        stdout: textSummary(data, { indent: " ", enableColors: true }),
    };
}

