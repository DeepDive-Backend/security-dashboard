function formatDateTime(date) {
    const pad = (value) => String(value).padStart(2, '0');
    return `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())} ` +
        `${pad(date.getHours())}:${pad(date.getMinutes())}:${pad(date.getSeconds())}`;
}

export function printTestStart() {
    console.log(`[TEST START] ${formatDateTime(new Date())}`);
}

export function printTestEnd() {
    console.log(`[TEST END] ${formatDateTime(new Date())}`);
}