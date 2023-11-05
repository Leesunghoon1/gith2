/**
 * 
 */
 
 // 트리거 버튼 처리

 document.getElementById('trigger').addEventListener('click', ()=>{
    document.getElementById('files').click();
 });

 //실행파일, 이미지파일에 대한 정규표현식 작성

 const regExp = new RegExp("\.(exe|sh|js|bat|msi|dll)$");
 //실행파일 체크
 const regExpImg = new RegExp("\.(jpg|jpeg|png|gif)$");
 //허용할 이미지 파일 체크
 const maxSize = 1024*1024*20; // 20MB 파일 최대 사이즈

 function fileValidation(fileName, fileSize) {
    if(!regExpImg.test(fileName)) { 
        //이미지 파일이 아니면 0
        return 0;
    }else if(regExp.test(fileName)) {
        //실행파일이면 0
        return 0;
    }else if(fileSize > maxSize) {
        //max사이즈보다 크면 0
        return 0;
    }else{
        //모두 해당 안되면 1
        return 1;

    }
 }

 document.addEventListener('change', (e)=>{
    if(e.target.id == 'files') {
        document.getElementById('button').disabled = false;
        //파일을 다시 추가할 때는 버튼 상태를 원래대로 변경

        const fileObj = document.getElementById('files').files;
        // input file element에 저장된 file의 정보를 가져오는 property
        console.log(fileObj);

        let div = document.getElementById('file-zone');
        // 첨부파일에 대한 정보를 file-zone에 기록

        div.innerHTML = "";
        // 기존 값이 있다면 삭제

        let isOK = 1;
        let ul = `<ul class="list-group">`;
        for(let file of fileObj) {
            let validResult = fileValidation(file.name, file.size);
             // 0 또는 1로 리턴
             isOK *= validResult;
             ul += `<li class="list-group-item">`;
             ul += `<div class="input-group mb-3">`;
             ul += `${validResult ? '<div class="input-group mb-3">업로드 가능</div>' : '<div class="input-group mb-3">업로드 불가능</div>'}`;
             ul += `${file.name}<span class="badge text-bg-${validResult ? 'success' : 'danger'}">${validResult ? 'success' : 'fail'}</span></div>`;

        }
        ul += `</ul>`;
        div.innerHTML = ul;

        if(isOk == 0){
            document.getElementById('button').disabled = true;
        }
    }
    
 })
