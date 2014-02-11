<%@ page pageEncoding="utf-8"  contentType="text/html;charset=utf-8" %>
<html>
 <head>
  <title>发布</title>
  <script src="js/jquery-1.10.2.js"></script>
  <script src="js/form-validator/jquery.form-validator.js"></script>
  <link href="css/fineuploader.css" rel="stylesheet">
 </head>
 <body>

<form action="" method="post" id="registration-form">
    <p>
      旅游地：
      <input name="name" data-validation="length required alphanumeric" 
		 data-validation-length="3-12" data-validation-error-msg="旅游地不能为空">
    </p>
    <p>
      出发日期：
      <input name="starttime" data-validation="required birthdate" 
		 data-validation-help="yyyy-mm-dd" data-validation-error-msg="出发日期不能为空">
    </p>
    <p>
      最低人数：
      <input name="minNum" id="country" data-validation="required numeric" data-validation-error-msg="最低人数不能为空">
    </p>
    <p>
    
  <p>
    描述信息
          还剩<span id="max-length-element">100</span>个字符
    <textarea id="the-textarea" name="description"></textarea>
  </p>
  
  
      图片文件
      <input type="file" name="img" data-validation="mime size" data-validation-allowing="jpg, png" 
		 data-validation-max-size="5Mb">
    </p>
    <p>
      <input type="submit">
    </p>
  </form>


<script type="text/javascript">
  
  $.validate({
	  form : '#registration-form',
	  modules : 'date,file',
	  
	  onModulesLoaded : function() {
		    alert('All modules is loaded!');
		  },
		  
	  onError : function() {
	      alert('Validation failed');
	    },	  
		  
	  onSuccess : function() {
	      alert('The form is valid!');
	      return false; // Will stop the submission of the form
	  },
	  
	  onValidate : function() {
	      return {
	        element : $('#some-input'),
	        message : 'This input has an invalid value for some reason'
	      }
	    }
	  });
  
  $('#the-textarea').restrictLength( $('#max-length-element') );
</script>

 </body>
</html>
