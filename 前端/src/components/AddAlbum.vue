<template>	<div >	<!-- 	<mt-header title="增添相册">		   		</mt-header> -->
		<van-nav-bar title="添加相册" left-arrow left-text="返回"  @click-left="goback()">
		 
		</van-nav-bar>		<div class="form">			<mt-field label="相册名" placeholder="请输入相册名" v-model="albumName"></mt-field>						<mt-button plain type="primary" size="large" @click="addAlbum()">确认添加</mt-button>		</div>			</div></template><script>	import { MessageBox,Toast } from 'mint-ui';	const md5=require("js-md5");
	import Cookies from 'js-cookie';export default {  name: 'AddAlbum',  data:function(){	  return{
	  albumName:""	  }  },  methods:{	  goback:function(){
		  var vue =this;
		  this.albumName="";		  this.$parent.conName="Main"
		  this.$root.axios.get("/server/user/refresh",{
		  	params:{
		  		username:vue.$root.$children[0].username
		  	}
		  }).then(function(res){
		  	vue.$root.$children[0].albums=res.data.data.albums;
		  	var albumsStr=JSON.stringify(res.data.data.albums);
		  	Cookies.set("albums",albumsStr);
		  	console.log(res)
		  }).catch(function(err){
		  	console.log(err)
		  });	  },
	  addAlbum:function(){
		  var vue=this;
		if(vue.albumName==""){
			Toast("请输入相册名");
			return;
		}
		   this.$root.axios.get("/server/user/addAlbum",{
		   	  params:{
		   		  username:this.$root.$children[0].username,
		   		  albumName:vue.albumName
		   	  }
		     }).then(function(res){
		   	  if(res.data.code==101){
		   		  Toast("add success");
		   		  }else{
		   					  Toast("add fail");
		   				  }}).catch(function(error){
		   		console.log(error)
		   });
		   
		   
		    
	  }  }}
</script><style>	.form{		margin-top:150px;	}<style>