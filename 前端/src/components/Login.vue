<template>
	<div class="login">
		<div class="logining">
			<div class="loginimg">
				<van-image
			     round
			     width="10rem"
			     height="10rem"
				 size="large"
			      src="https://img01.yzcdn.cn/vant/cat.jpeg"
			/>
			</div>
			<van-form  class="loginform">
			  <van-field
			    v-model="username"
			    name="用户名"
			    label="用户名"
			    placeholder="用户名"
				
			  />
			  <van-field
			    v-model="password"
			    type="password"
			    name="密码"
				label="密码"
			    placeholder="密码"
			  />
			  <div style="margin: 16px;" @click="login()">
			    <van-button round block type="info"  color="linear-gradient(to right, #667db6, #0082c8, #0082c8, #667db6)" >登录</van-button>
			  </div>
			</van-form>
			<div >
				<a  @click="toRegist()">无账号请点击注册</a>
			</div>
			
		</div>
	</div>
</template>

<script>
import Vue from 'vue';
import { Image as VanImage,Form,Field,Toast} from 'vant';
import Cookies from 'js-cookie';
const md5=require("js-md5")
Vue.use(VanImage);
Vue.use(Form);
Vue.use(Field);
Vue.use(Toast);
export default {
  name: 'Login',
  data() {
      return {
        username: "",
        password: "",
		token:""
      };
    },
	methods:{
		login:function(){
			if(this.username==""||this. password==""){
			   Toast("usrename or password is null");
			   return;
			}
			 md5(this. password);
			 var hash=md5.create();
			 hash.update(this. password);
			 let hexpwd=hash.hex();
			 
			 //验证是否输入
			 var vue=this
			 this.$root.axios.get("/server/user/login",{
				 params:{
					 username:vue.username,
					 pwd:hexpwd
					 // pwd:vue.password
				 }
			 }).then(function(res){
				 if(res.data.code==101){
					Toast.success('登录成功，跳转中...');
					vue.$parent.username=res.data.data.username;
					vue.$parent.token=res.data.data.token;
					vue.$parent.albums=res.data.data.albums;
					Cookies.set("username",res.data.data.username);
					Cookies.set("token",res.data.data.token);
					var albumsStr=JSON.stringify(res.data.data.albums);
					 Cookies.set("albums",albumsStr);
					 vue.$parent.conName="Main";
				 }else{
					 Toast.fail('账号或者密码不正确');
				 }
			 }).catch(function(error){
				 console.log(error)
			 });
		},
		toRegist:function(){
			this.$parent.conName="Register"
		}
	}
	
	
}
</script>

<style>
	.logining{
		display: flex;
		flex-direction:column;
		align-items: center;
		position: absolute;
		left: 50%;
		top: 50%;
		transform: translate(-50%,-50%);
		font-size: 12px;
	}
	.loginimg{
		margin-bottom: 10px;
	}
	.loginform{
		width: 80vw;
	}
</style>
