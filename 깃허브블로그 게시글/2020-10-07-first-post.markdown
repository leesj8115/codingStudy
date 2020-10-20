---
layout: post
title: "Git Blog 만들기 과정"
date: 2020-10-07 23:56:20 +0900
description: 다른 게시글을 보면서 따라서 만든 Git Blog의 제작 과정과 잡담 # Add post description (optional)
img: makeBlog.png # Add image post (optional)
---

# Git Blog 따라서 만들어보자

많은 개발자들이 자신의 포트폴리오를 정리하기 위해 블로그나 Git 등 여러가지를 이용하지만, 정작 스스로는 하고 있는 게 아무것도 없었습니다.

스터디에서도 블로그의 중요성을 강조하여 블로그를 만들기로 마음먹었습니다!

티스토리 밖에 몰랐던 나에게 스터디에서 `Git Blog`를 알려주었습니다. Git의 무료 계정에서도 개인용 블로그를 운용할 수 있게 도와주고 있어, 이 기회에 Git Blog를 만들기로 도전했습니다.

첫 게시글도 올릴 겸, 참고했던 사이트인 [차근차근_Git_블로그_만들기](https://zoomkoding.github.io/gitblog/2019/08/15/git-blog-1.html)를 바탕으로 다시 한 번 Git Blog 만들기를 정리합니다. 설명이 부족한 부분은 해당 링크를 참고해 주세요.

---

## 1. repository 생성

![레포 생성]({{site.baseurl}}/assets/img/makeBlog/newRepo.jpg)     
blog로 활용될 레파지토리를 생성한다. 다들 알고 있겠지만, git에서 로그인하여 우측 상단에 `+`를 눌러 'New repository'를 클릭합니다.


![레포 이름]({{site.baseurl}}/assets/img/makeBlog/repoNaming.jpg)     
이후에 레파지토리의 이름을 설정하는 항목이 있습니다. 여기에는 꼭! **`[본인 id]`.github.io**의 형태가 되도록 합니다.     
나머지 항목도 확인해줍시다. **Public**으로 체크하며<sup>[1](#footnote_1)</sup>, 아래의 초기화 항목은 모두 체크 해제합니다.     
특히, `Add a README file` 항목을 클릭했을 때는 기본 설정상 main 의 별도 brunch에 readme.md 파일을 생성하는데, 브런치 사용이 익숙치 않은 나같은 초보자는 이후 과정에서 혼동이 없도록<sup>[2](#footnote_2)</sup> 체크를 해제합니다.

![레포 링크]({{site.baseurl}}/assets/img/makeBlog/copyLink.jpg)    
위의 과정이 끝나면 텅텅 빈 레파지토리가 완성됩니다. 여기 중에서 파란색 부분을 봐주세요. 여기의 링크는 레파지토리에 접근하기 위한 링크이니, 잘 복사해둡시다. (빨간 네모 상자 안의 버튼을 누르면 자동으로 클립에 복사가 됩니다.)


## 2. Jekyll Theme 적용하기
테마를 설치하기 위한 준비를 합시다. 레포가 저장될 디렉토리를 지정하여 명령어를 입력해줍시다. 이 때 위에서 복사란 링크를 활용합시다.

git 명령어가 오류가 날 경우에는 [Git을_설치](https://goddaehee.tistory.com/216)합시다.

> cd [Repository를 저장할 폴더]
> git clone [위의 과정에서 복사한 링크]

위의 코드를 입력하면 다음과 같은 결과가 나옵니다.
![레포 폴더 생성]({{site.baseurl}}/assets/img/makeBlog/code1.jpg)    

repo라는 폴더를 만들어 그 안에 복사를 했습니다. clone 명령어 실행 뒤 나오는 오류는 빈 레포를 복사했기 때문에 나오는 내용으로 무시해도 됩니다. 

![레포 폴더 생성 결과]({{site.baseurl}}/assets/img/makeBlog/code1Result.jpg)   
실제로 탐색기로 확인해보면 폴더가 만들어졌음을 볼 수 있습니다.

이제 원하는 테마를 선택해봅시다. [다른_블로그_게시글]을 참조하여 테마를 고르겠습니다. 테마 제공 사이트 목록 중 2번째인 https://jekyllthemes.io/free 를 이용하겠습니다. 첫번째 링크에 비해 언제 commit된 테마인지 확인할 수 있어 좋았습니다. 여러 테마 중 마음에 드는 것을 고르면 되는데, 저는 [Flexible-Jektll](https://jekyllthemes.io/theme/flexible-jekyll)을 선택했습니다.

![테마 결정]({{site.baseurl}}/assets/img/makeBlog/choiceTheme.jpg)   
스크롤을 내려 `Get [테마 이름] on Github` 버튼을 누르면 해당 테마가 있는 git으로 이동합니다.

![테마 파일 복사]({{site.baseurl}}/assets/img/makeBlog/saveTheme.jpg)   
페이지로 들어와서 CODE - Download ZIP 을 클릭하여 테마에 필요한 파일을 저장합니다.

이후 저장한 파일은 위에서 만들어주었던 레포의 폴더에 복사해줍니다.

다음에는 `_config.yml`에 있는 기본 설정 값을 수정해야합니다. 나의 정보에 맞게 수정하는 것은 다음에 하고, 테마가 적용될 수 있도록 중요한 사항만 수정합시다.
```yml
baseurl: "" # 비워둡시다
url: "https://leesj8115.github.io" # 자신의 계정 이름을 포함한 레포 링크를 넣어줍니다.
```
다른 값들도 본인에 맞게 수정해야하지만, 그것은 블로그가 테마에 맞게 만들어진 이후에 수정해도 늦지 않으니 천천히 해봅시다. 

이제 다운로드한 테마 파일을 적용시키겠습니다. 우선 bundler를 설치합니다.
<sup>[3](#footnote_3)</sup>  
gem 명령어가 오류가 날 경우에는 [루비를_설치](https://www.ruby-lang.org/ko/documentation/installation/)합니다. ([gem이란](https://ideveloper2.tistory.com/80)) 
> cd [Reepository를 저장한 폴더]   
> gem install bundler   
> bundle install

그리고 git에 업로드하겠습니다.
> git add .   
> git commit -m "Theme changed"   
> git push

현재 위치(.)에 있는 테마 파일을 모두 선택하여 푸쉬하는 명령어입니다. 이 때 "테마 변경"이라는 주석을 달아 후에도 확인할 수 있도록 해주었습니다.   
![테마 git 업로드]({{site.baseurl}}/assets/img/makeBlog/pushTheme.jpg)   


## 3. Github Page 확인
이후 레포의 `Setting`을 클릭 후 스크롤을 내리면 `Github Page` 탭을 확인할 수 있습니다.   
![페이지 확인]({{site.baseurl}}/assets/img/makeBlog/checkPage.jpg)  
위의 사진처럼 초록색으로 링크가 연결되었다는 메세지가 보인다면 접속했을 때 블로그가 정상적으로 반영됐음을 확인할 수 있습니다.

만약 그렇지 않다면, 아래의 2가지 조건 (Source, Theme Chooser)를 조정합니다.
 - Source : 테마 파일이 업로드 된 위치
 - Theme Chooser : 기본 제공되는 테마 선택. 테마를 별도로 구했으므로 쓸 일이 없는데, 만약 블로그가 적용되지 않았을 경우에 저의 경우 해당 목록 중에 아무거나 선택한 후, `_config.yml` 파일에 들어가서 맨 마지막 줄에 추가된 "`theme:`로 기입된 내용"을 다시 삭제하고 갱신했습니다.

## 블로그 시작
![완성!]({{site.baseurl}}/assets/img/makeBlog.png)  
위 과정까지 끝나고, 마지막으로 링크로 들어가면 아름다운 블로그를 보실 수 있습니다. 글을 작성하는 방법은 최초 참고한 링크를 확인해주세요.

저는 블로그 주인에 대한 내용을 우선 수정하고, 게시글은 차차 쌓아볼 예정입니다. 확실히 일반 블로그처럼 다 만들어주는 게 아니라서 그런지는 몰라도, 초보자인 저에겐 이것을 만드는 일 조차 오랜 시간이 걸렸습니다. 다른 분들은 내용을 채우는데 더 시간을 할애하길 바라며 정리 글을 마칩니다.

-----

<a name="footnote_1">1</a>: 무료 계정에서는 Public에 대해서만 Github Page 기능을 이용할 수 있습니다.

<a name="footnote_2">2</a>: 별도의 brunch를 생성하지 않는다면, 'master'를 default 단일 브런치로 사용하게 됩니다.

<a name="footnote_3">3</a>: 이 과정은 테마의 적용에 필요한 과정으로 볼 수 있겠습니다. (정확한 이유는 더 공부해서 알아봐야할 것 같습니다..)
