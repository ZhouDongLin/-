<template>	<div >
		<mt-header title="用户登入">
		</mt-header>
		<div class="form">
			<mt-field label="用户名" placeholder="请输入用户名" v-model="username"></mt-field>
			<mt-field label="密码" placeholder="请输入密码" type="password" v-model="pwd"></mt-field>
			<mt-button type="primary" size="large" @click="login()">登入</mt-button>
			<a  class="toRig" @click="toRegister()">没有账号?点击注册</a>
		</div>
		
	</div></template><script>
import { MessageBox,Toast } from 'mint-ui';
import Cookies from 'js-cookie'
const md5=require("js-md5")export default {  name: 'Login',
  data:function(){
	  return {
		  username:"",
		  pwd:"",
		  token:""
	  }
  },
  methods:{
	  login:function(){
		  if(this.username==""||this.pwd==""){
		  	  Toast("usrename or password is null");
		  	  return;
		    }
		    md5(this.pwd);
		    var hash=md5.create();
			hash.update(this.pwd);
		    let hexpwd=hash.hex();
		    
		    //验证是否输入
		    var vue=this
		    this.$root.axios.get("/server/user/login",{
		  	  params:{
		  		  username:vue.username,
		  		  pwd:hexpwd
		  	  }
		    }).then(function(res){
		  	  if(res.data.code==101){
		  		  Toast("登入成功,跳转中...");
				  //存储信息
				  vue.$parent.username=res.data.data.username;
				  vue.$parent.token=res.data.data.token;
				  vue.$parent.albums=res.data.data.albums;
				  //存cookie
				  Cookies.set("username",res.data.data.username);				  Cookies.set("token",res.data.data.token);
				  var albumsStr=JSON.stringify(res.data.data.albums)
				  Cookies.set("albums",albumsStr);
				  //跳转到Main组件
				  vue.$parent.conName="Main"
		  	  }else{
				  Toast("账号或者密码不正确");
			  }
		    }).catch(function(error){
		  		console.log(error)
		  });
	  },
	  toRegister:function(){
		  this.$parent.conName='Register'
	  }
  }}	</script><style>
	.toRig{
		font-size: 14px;
		padding-top: 30px;
	}</style>