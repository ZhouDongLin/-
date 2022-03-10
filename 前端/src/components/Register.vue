<template>
	<div>
		<van-nav-bar title="注册" left-text="返回"  @click-left="goback()" left-arrow  >
		 
		</van-nav-bar>
		<div class="registing">
			
			<div class="registimg">
				<van-image
			     round
			     width="10rem"
			     height="10rem"
				 size="large"
			      src="https://img01.yzcdn.cn/vant/cat.jpeg"
			/>
			</div>
			<van-form @submit="onSubmit" class="registform">
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
			  <div style="margin: 16px;" >
			    <van-button round block type="info" native-type="submit" color="linear-gradient(to right, #667db6, #0082c8, #0082c8, #667db6)" @click="regist()">注册</van-button>
			  </div>
			</van-form>
			
		</div>
	</div>
	
</template>

<script>
	import Vue from 'vue';
	import { Image as VanImage,Form,Field,Toast } from 'vant';
	import { Dialog} from 'vant';
	const md5=require("js-md5")
	Vue.use(VanImage);
	Vue.use(Form);
	Vue.use(Field);
	Vue.use(Toast);
	export default {
	  name: 'Register',
	  data() {
	      return {
	        username: '',
	        password: '',
	      };
	    },
	    methods: {
			goback:function(){
					  
					  this.$parent.conName="Login"
			},
	      onSubmit(values) {
	        console.log('submit', values);
	      },
		  regist:function(){
			if(this.username==""||this.password==""){
				Toast("usrename or password is null");
				return;
				
			}
			md5(this.password);
			var hash=md5.create();
			hash.update(this.password);
			let hexpwd=hash.hex();
				//z注意传参，后端获取的参数是pwd
			var vue=this
			this.$root.axios.get("/server/user/register",{
				params:{
					 username:vue.username,
					 pwd:hexpwd
				}
			}).then(function(res){
				if(res.data.code==101){
					Dialog.alert({
					  message: '跳转到登录界面',
					}).then(() => {
					  // on close
					  vue.$parent.conName="Login"
					});
				}else{
					Toast.fail("注册失败!用户名已经存在!");
				}
			}).catch(function(error){
				console.log(error)
			});
		  }
	    }
	}
</script>

<style>
	.registing{
		display: flex;
		flex-direction:column;
		align-items: center;
		position: absolute;
		left: 50%;
		top: 50%;
		transform: translate(-50%,-50%);
		font-size: 12px;
	}
	.registimg{
		margin-bottom: 10px;
	}
	.registform{
		width: 80vw;
	}
</style>
