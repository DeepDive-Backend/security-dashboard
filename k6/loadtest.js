import http from 'k6/http';
import { check } from 'k6';
import {printTestEnd, printTestStart} from "./common/lifecycle.js";

const BASE_URL = __ENV.BASE_URL || 'http://localhost:8080';
const TYPE = __ENV.TYPE || 'JPQL';

export const options = {
    stages: [
        { duration: '30s', target: 10 },
        { duration: '30s', target: 30 },
        { duration: '30s', target: 50 },
        { duration: '30s', target: 100 },
        { duration: '10s', target: 0 },
    ],

    thresholds: {
        http_req_failed: ['rate<0.01'],
        http_req_duration: ['p(95)<1000'],
    },
};

export function setup() {
    printTestStart();
}

export default function () {
    const eventTypeResponse = http.get(
        `${BASE_URL}/aggregation/event-type?type=${TYPE}`,
        {
            tags: {
                api: 'event-type',
            },
        }
    );

    check(eventTypeResponse, {
        'event-type status is 200': (response) => response.status === 200,
    });
}

export function teardown() {
    printTestEnd();
}