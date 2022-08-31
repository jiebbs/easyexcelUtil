# 前端配套下载文件JS
export const downLoadFile = function(fileName, fileContent) {
  if ('download' in document.createElement('a')) { // 非IE下载
    var a = document.createElement('a')
    var bstr = atob(fileContent)
    var n = bstr.length
    var u8arr = new Uint8Array(n)
    while (n--) {
      u8arr[n] = bstr.charCodeAt(n)
    }
    var blob = new Blob([u8arr], { type: 'application/octet-stream' })
    var url = window.URL.createObjectURL(blob)
    a.href = url
    a.download = fileName
    a.click()
    window.URL.revokeObjectURL(url)
  } else { // IE10+下载
    navigator.msSaveBlob(blob, fileName)
  }
}