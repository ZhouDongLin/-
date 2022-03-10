<template>
	<div class="my">
		<van-nav-bar title="我的主页"  >
		</van-nav-bar>
		<div class="my_head">
			<van-image
			     round
			     width="20vh"
			     height="20vh"
				 size="large"
			      src="https://img01.yzcdn.cn/vant/cat.jpeg"
			/>
			<p>hi～尊敬的：{{this.$root.$children[0].username}}</p>
		</div>
	    <div class="my_body">
			<div class="my_text">
			  <van-cell-group inset>
				
				 <!-- <van-cell title="我的相册" value=""  icon="orders-o" size="large" /> -->
				  <van-cell title="修改密码" value=""  icon="edit"  size="large" @click="goreply()"/>
				  <van-cell title="退出登录" value=""  icon="close" size="large" @click="logout()"/>
			  </van-cell-group>
			</div>
			
		</div>
	</div>
</template>

<script>
import Vue from 'vue';
import { Image as VanImage,Form,Field } from 'vant';
import { Cell, CellGroup } from 'vant';
Vue.use(Cell);
Vue.use(CellGroup);
Vue.use(VanImage);
import Cookies from 'js-cookie'
export default {
  name: 'My',
  methods:{
	  goreply:function(){
		  this.$root.$children[0].conName="Reply"
	  },
	  logout:function(){
	  	Cookies.set("username","");
	  	Cookies.set("token","");
	  	Cookies.set("albums","");
	  	this.$root.$children[0].username="";
	  	this.$root.$children[0].token="";
	  	this.$root.$children[0].albums=[];
	  	this.$root.$children[0].conName="Login";
	  }
  }
}
</script>

<style>
.my{
	position: relative;
	height: 93vh;
	width: 100vw;
	background:#e4e4d9;
}
.my .my_head{
	display: flex;
	align-items: center;
	justify-content: center;
	position: absolute;
	top: 17vh;
	left: 3vh;
	z-index: 99;
}
.my .my_head p{
	padding: 3vh;
}
.my .my_body{
	position: absolute;
	top: 35vh;	
	height: 70vh;
	width: 100vw;
    padding-top: 6vh;
	background: rgb(247,248,250);
}


</style>
