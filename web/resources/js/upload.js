/**
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-07
 Time: 오후 2:17
 desc: upload javascript file

 Created by altjd815@gmail.com
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 **/

function checkImageType(fileName) {
    var pattern = /jpg$|gif$|png$|jpeg$/i;

    return fileName.match(pattern);
};

function getFileInfo(fullName) {
    var fileName, imgSrc, getLink;
    var fileLink;
    var bno = "${bVO.bno}";

    if (checkImageType(fullName)) {
        imgSrc = "/boards/" + bno + "/displayFile?fileName=" + fullName;
        fileLink = fullName.substr(14);

        var front = fullName.substr(0, 12);
        var end = fullName.substr(14);

        getLink = "/boards/" + bno + "displayFile?fileName=" + front + end;
    } else {
        imgSrc = "/resources/dist/img/file.png";
        fileLink = fullName.substr(12);
        getLink = "/boards/" + bno + "/displayFile?fileName=" + fullName;
    }

    fileName = fileLink.substr(fileLink.indexOf("_") + 1);

    return {fileName:fileName, imgSrc:imgSrc, getLink:getLink, fullName:fullName};
}
