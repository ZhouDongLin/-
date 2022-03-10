<template>	<div >		<mt-header title="用户注册">		  <!-- <router-link to="/" slot="left" > -->		    <mt-button icon="back" @click="goback()" slot="left">返回</mt-button>		  <!-- </router-link> -->		  <mt-button icon="more" slot="right"></mt-button>		</mt-header>
		<div class="form">
			<mt-field label="用户名" placeholder="请输入用户名" v-model="username"></mt-field>
			<mt-field label="密码" placeholder="请输入密码" type="password" v-model="pwd"></mt-field>
			<mt-button type="primary" size="large" @click="regist()">注册</mt-button>
		</div>			</div></template><script>	import { MessageBox,Toast } from 'mint-ui';	const md5=require("js-md5")export default {  name: 'Register',  data:function(){	  return{		  username:"",		  pwd:""	  }  },  methods:{	  regist:function(){
			  
		  if(this.username==""||this.pwd==""){
			  Toast("usrename or password is null");
			  return;
		  }		  md5(this.pwd);		  var hash=md5.create();
		  hash.update(this.pwd);		  let hexpwd=hash.hex();		  		  //验证是否输入		  var vue=this		  this.$root.axios.get("/server/user/register",{			  params:{				  username:vue.username,				  pwd:hexpwd			  }		  }).then(function(res){			  if(res.data.code==101){				  MessageBox.alert('跳转到登录').then(action => {				    //改变app.vue中的conName值					vue.$parent.conName="Login"				  });			  }		  }).catch(function(error){				console.log(error)		});	  },	  goback:function(){		  this.$parent.conName="Login"	  }  }}	</script><style>	.form{		margin-top:150px;	}</style>