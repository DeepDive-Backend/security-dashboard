import http from 'k6/http';
import { check } from 'k6';

const BASE_URL = __ENV.BASE_URL || 'http://localhost:8080';
const TYPE = __ENV.TYPE || 'NATIVE';

export default function () {

    const res = http.get(
        `${BASE_URL}/aggregation/event-type?type=${TYPE}`
    );

    check(res, {
        'status is 200': (r) => r.status === 200,
    });

}