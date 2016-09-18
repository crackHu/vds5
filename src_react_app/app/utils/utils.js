let baseUrl
if (process.env.NODE_ENV === 'production') {
  baseUrl = ''
} else {
  baseUrl = ''
}

export const config = {
  baseUrl: baseUrl,
  host: 'http://www.basoft.cn/'
}

// 对URL地址进行拆分，返回其中附带的值
// a返回对象,b值得字符串集合,c子值（即类似于"XX=XX"）
// 当没有附带值是返回false
export function getUrlVal(url) {
  let a = {},
    b, c, u
  url = !url ? window.location.href : url
  u = url.split('?')
  if (u.length === 1) {
    return false
  } else {
    b = u[1].split('&')
    for (var i = 0; i < b.length; i++) {
      c = b[i].split('=')
      a[c[0]] = c[1]
    }
  }
  return a
}

//检查字段是否为空或不存在  
export function checkParams(params) {
  if (params === null || params === 'undefined' || !params)
    return ''
  else
    return params
}

export function getDate() {
  Date.prototype.yyyymmdd = function() {
    var yyyy = this.getFullYear().toString()
    var mm = (this.getMonth() + 1).toString() // getMonth() is zero-based
    var dd = this.getDate().toString()
    return yyyy + '-' + (mm[1] ? mm : '0' + mm[0]) + '-' + (dd[1] ? dd : '0' + dd[0]) // padding
  }

  var d = new Date()
  return d.yyyymmdd()
}

export function getIndexOfObjWithKeyVal(array, key, value) {
  for (var i = 0; i < array.length; i++) {
    if (array[i].hasOwnProperty(key) && array[i][key] === value) {
      return i;
    }
  }
  return -1;
}

export function emptyObject(obj) {
  return Object.keys(obj).length === 0 && obj.constructor === Object
}

//============================== 生成uuid ==============================
export function randomUUID() {
  var s = [],
    itoh = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'];

  // Make array of random hex digits. The UUID only has 32 digits in it, but we
  // allocate an extra items to make room for the '-'s we'll be inserting.
  for (var i = 0; i < 36; i++) {
    s[i] = Math.floor(Math.random() * 0x10);
  }

  // Conform to RFC-4122, section 4.4
  s[14] = 4; // Set 4 high bits of time_high field to version
  s[19] = (s[19] & 0x3) | 0x8; // Specify 2 high bits of clock sequence


  // Convert to hex chars
  for (var i = 0; i < 36; i++) {

    s[i] = itoh[s[i]];

  }

  // Insert '-'s
  s[8] = s[13] = s[18] = s[23] = '-';

  return s.join('');
}