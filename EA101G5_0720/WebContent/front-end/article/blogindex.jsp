<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.coach.model.*"%>
<%@ page import="com.student.model.*"%>
 <%@ include file="/front-end/header.jsp" %>
<!DOCTYPE html>


<%
  ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO"); 

  CoaService coachSvc = new CoaService();
  List<CoaVO> coalist = coachSvc.getAll();
  pageContext.setAttribute("coalist",coalist);

  StuService studentSvc = new StuService();
  List<StuVO> stulist = studentSvc.getAll();
  pageContext.setAttribute("stulist",stulist);

%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>首頁</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/article/images/me.css">
  <!-- Favicon  -->
  <link rel="icon" href="<%=request.getContextPath()%>/images/core-img/favicon.ico">

  <!-- Core Style CSS -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/css/core-style.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC&display=swap" rel="stylesheet">

<body bgcolor='white'>


   <!--中間内容-->
  <div id="waypoint" class="m-container-small m-padded-tb-big animated fadeIn">
    <div class="ui container">
      <div class="ui top attached segment">
        <div class="ui horizontal link list">
          <div class="item">
<!--             <img src="https://unsplash.it/100/100?image=1005" alt="" class="ui avatar image"> -->
            <div class="content"><a href="#" class="header">
             <c:forEach var="StuVO" items="${stulist}" > <c:if test ="${StuVO.stuno == articleVO.stuNo}" >${StuVO.stuname}</c:if></c:forEach>  
             <c:forEach var="CoaVO" items="${coalist}" > <c:if test ="${CoaVO.coano == articleVO.coaNo}" >${CoaVO.coaname}</c:if></c:forEach>
            </a></div>
          </div>
          <div class="item">
            <i class="calendar icon"></i>${articleVO.artDate}
          </div>
        </div>
      </div>
      <div class="ui attached segment">
        <!--圖-->
         <img src="<%=request.getContextPath()%>/ProductReader?artno=${articleVO.artNo}" width=800px height=450px class="ui fluid rounded image">
      </div>
      <div class="ui  attached padded segment">
        <!--内容-->

        <h2 class="ui center aligned header">${articleVO.artTitle}</h2> <!--  title -->
        <br>




        <div id="content" class="typo  typo-selection js-toc-content m-padded-lr-responsive m-padded-tb-large">
         ${articleVO.artCon}
        </div>
        
        

      </div>
      
      <div class="ui attached positive message">
        <!--博客信息-->
        <div class="ui middle aligned grid">
          <div class="eleven wide column">
            <ui class="list">
            
              <li>作者:
             <c:forEach var="StuVO" items="${stulist}" > <c:if test ="${StuVO.stuno == articleVO.stuNo}" >${StuVO.stuname}</c:if></c:forEach>  
             <c:forEach var="CoaVO" items="${coalist}" > <c:if test ="${CoaVO.coano == articleVO.coaNo}" >${CoaVO.coaname}</c:if></c:forEach>
              </li>
           
<%--              <c:if test="${articleVO.coaNo}!=null"> --%>
<%--               <li>作者：${articleVO.coaNo}</li> --%>
<%--              </c:if> --%>
              <li>發表時間：${articleVO.artDate}</li>
            </ui>
          </div>
          <div class="five wide column">
            <img src="../static/images/wechat.jpg" alt="" class="ui right floated rounded bordered image" style="width: 110px">
          </div>
        </div>
      </div>
      </div>
      </div>
      
      
      <br><br><br>
<!--       <div id="comment-container" class="ui bottom attached segment"> -->
      
      
<!--         留言區域列表 -->
<!--         <div class="ui teal segment"> -->
<!--           <div class="ui threaded comments"> -->
<!--             <h3 class="ui dividing header">Comments</h3> -->
<!--             <div class="comment"> -->
<!--               <a class="avatar"> -->
<!--                 <img src="https://unsplash.it/100/100?image=1005"> -->
<!--               </a> -->
<!--               <div class="content"> -->
<!--                 <a class="author">Matt</a> -->
<!--                 <div class="metadata"> -->
<!--                   <span class="date">Today at 5:42PM</span> -->
<!--                 </div> -->
<!--                 <div class="text"> -->
<!--                   How artistic! -->
<!--                 </div> -->
<!--                 <div class="actions"> -->
<!--                   <a class="reply">回復</a> -->
<!--                 </div> -->
<!--               </div> -->
<!--             </div> -->
<!--             <div class="comment"> -->
<!--               <a class="avatar"> -->
<!--                 <img src="https://unsplash.it/100/100?image=1005"> -->
<!--               </a> -->
<!--               <div class="content"> -->
<!--                 <a class="author">Elliot Fu</a> -->
<!--                 <div class="metadata"> -->
<!--                   <span class="date">Yesterday at 12:30AM</span> -->
<!--                 </div> -->
<!--                 <div class="text"> -->
<!--                   <p>This has been very useful for my research. Thanks as well!</p> -->
<!--                 </div> -->
<!--                 <div class="actions"> -->
<!--                   <a class="reply">回復</a> -->
<!--                 </div> -->
<!--               </div> -->
<!--               <div class="comments"> -->
<!--                 <div class="comment"> -->
<!--                   <a class="avatar"> -->
<!--                     <img src="https://unsplash.it/100/100?image=1005"> -->
<!--                   </a> -->
<!--                   <div class="content"> -->
<!--                     <a class="author">Jenny Hess</a> -->
<!--                     <div class="metadata"> -->
<!--                       <span class="date">Just now</span> -->
<!--                     </div> -->
<!--                     <div class="text"> -->
<!--                       Elliot you are always so right :) -->
<!--                     </div> -->
<!--                     <div class="actions"> -->
<!--                       <a class="reply">回復</a> -->
<!--                     </div> -->
<!--                   </div> -->
<!--                 </div> -->
<!--               </div> -->
<!--             </div> -->
<!--             <div class="comment"> -->
<!--               <a class="avatar"> -->
<!--                 <img src="https://unsplash.it/100/100?image=1005"> -->
<!--               </a> -->
<!--               <div class="content"> -->
<!--                 <a class="author">Joe Henderson</a> -->
<!--                 <div class="metadata"> -->
<!--                   <span class="date">5 days ago</span> -->
<!--                 </div> -->
<!--                 <div class="text"> -->
<!--                   Dude, this is awesome. Thanks so much -->
<!--                 </div> -->
<!--                 <div class="actions"> -->
<!--                   <a class="reply">回復</a> -->
<!--                 </div> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="ui form"> -->
<!--           <div class="field"> -->
<!--             <textarea name="content" placeholder="請輸入留言..."></textarea> -->
<!--           </div> -->
<!--           <div class="fields"> -->
<!--             <div class="field m-mobile-wide m-margin-bottom-small"> -->
<!--               <div class="ui left icon input"> -->
<!--                 <i class="user icon"></i> -->
<!--                 <input type="text" name="nickname" placeholder="姓名"> -->
<!--               </div> -->
<!--             </div> -->
<!--             <div class="field m-mobile-wide m-margin-bottom-small"> -->
<!--               <div class="ui left icon input"> -->
<!--                 <i class="mail icon"></i> -->
<!--                 <input type="text" name="email" placeholder="電子信箱"> -->
<!--               </div> -->
<!--             </div> -->
<!--             <div class="field  m-margin-bottom-small m-mobile-wide"> -->
<!--               <button class="ui teal button m-mobile-wide"><i class="edit icon"></i>發佈</button> -->
<!--             </div> -->
<!--           </div> -->

<!--         </div> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->

<!--   <div id="toolbar" class="m-padded m-fixed m-right-bottom" style="display: none"> -->
<!--     <div class="ui vertical icon buttons "> -->
<!--       <button type="button" class="ui toc teal button" >目錄</button> -->
<!--       <a href="#comment-container" class="ui teal button" >留言</a> -->
<!--       <button class="ui wechat icon button"><i class="weixin icon"></i></button> -->
<!--       <div id="toTop-button" class="ui icon button" ><i class="chevron up icon"></i></div> -->
<!--     </div> -->
<!--   </div> -->

<!--   <div class="ui toc-container flowing popup transition hidden" style="width: 250px!important;"> -->
<!--     <ol class="js-toc"> -->

<!--     </ol> -->
<!--   </div> -->

<!--   <div id="qrcode" class="ui wechat-qr flowing popup transition hidden "style="width: 130px !important;"> -->
<!--     <img src="./static/images/wechat.jpg" alt="" class="ui rounded image" style="width: 120px !important;"> -->
<!--   </div> -->



<script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/semantic-ui/2.2.4/semantic.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/jquery.scrollto@2.1.2/jquery.scrollTo.min.js"></script>
  
<script src="../static/lib/prism/prism.js"></script>
<script src="../static/lib/tocbot/tocbot.min.js"></script>
<script src="../static/lib/qrcode/qrcode.min.js"></script>
<script src="../static/lib/waypoints/jquery.waypoints.min.js"></script>



  <script>

    $('.menu.toggle').click(function () {
      $('.m-item').toggleClass('m-mobile-hide');
    });

    tocbot.init({
      // Where to render the table of contents.
      tocSelector: '.js-toc',
      // Where to grab the headings to build the table of contents.
      contentSelector: '.js-toc-content',
      // Which headings to grab inside of the contentSelector element.
      headingSelector: 'h1, h2, h3',
    });

    $('.toc.button').popup({
      popup : $('.toc-container.popup'),
      on : 'click',
      position: 'left center'
    });

    $('#toTop-button').click(function () {
      $(window).scrollTo(0,500);
    });


    var waypoint = new Waypoint({
      element: document.getElementById('waypoint'),
      handler: function(direction) {
        if (direction == 'down') {
          $('#toolbar').show(100);
        } else {
          $('#toolbar').hide(500);
        }
        console.log('Scrolled to waypoint!  ' + direction);
      }
    })
  </script>

</body>
</html>
<%@ include file="/front-end/footer.jsp" %>