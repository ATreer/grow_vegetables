//判断 对象是空
export function isNull(param) {
    return param === undefined || param === null || param.length === 0 || Object.keys(param).length === 0 || param === '';
}