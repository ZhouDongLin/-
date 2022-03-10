<template>
	<div>
		<van-nav-bar title="修改密码" left-text="返回"  @click-left="goback()" left-arrow  >
		 
		</van-nav-bar>
		<div class="replying">
			<div class="replytimg">
				<van-image
			     round
			     width="10rem"
			     height="10rem"
				 size="large"
			      src="https://img01.yzcdn.cn/vant/cat.jpeg"
			/>
			</div>
			<van-form  class="replyform">
			  <van-field
			    name="原密码"
				type="password"
			    label="原密码"
			    placeholder="原密码"
				v-model="oldpwd"
			  />
			  <van-field
			    type="password"
			    name="新密码"
				label="新密码"
			    placeholder="新密码"
				v-model="newpwd"
			  />
			  <van-field
			    type="password"
			    name="确认密码"
			  	label="确认密码"
			    placeholder="确认密码"
				v-model="repwd"
			  />
			  <div style="margin: 16px;">
			    <van-button round block type="info" native-type="submit" color="linear-gradient(to right, #667db6, #0082c8, #0082c8, #667db6)" @click="replypwd()">确定</van-button>
			  </div>
			</van-form>		
		</div>
	</div>
</template>

<script>
import Vue from 'vue';
import { Image as VanImage,Form,Field,Toast } from 'vant';
import Cookies from 'js-cookie'
import { NavBar } from 'vant';Vue.use(NavBar);
Vue.use(VanImage);
Vue.use(Form);
Vue.use(Field);
Vue.use(Toast);
const md5=require("js-md5")
export default {
	name: 'Replay',
	data() {
	    return {
	      username: '',
	      oldpwd: '',
		  newpwd:'',
		  repwd:''
	    };
	},
	methods:{
		goback:function(){		
			this.$parent.conName="Main";
		},
		replypwd:function(){
			let um = Cookies.get("username");
			let vue=this;
			console.log(um);
			if(this.oldpwd==""||this.newpwd==""||this.repwd==""){
				Toast.fail("输入不能为空");
				return;
			}else if(this.newpwd!==this.repwd){
				Toast.fail("新密码与确认密码不相符");
				return;
			}else{
				md5(this.oldpwd);
				md5(this.newpwd);
				var hashold=md5.create();
				var hashnew=md5.create();
				hashold.update(this. oldpwd);
				hashnew.update(this. newpwd);
				let oldhexpwd=hashold.hex();
				let newhexpwd=hashnew.hex();
				this.$root.axios.get("/server/user/modifyPwd",{
					params:{
						username:um,
						passwordOld:oldhexpwd,
						passwordNew:newhexpwd
					}
				}).then(function(res){
					if(res.data.code==101){
						Toast.success('修改成功，跳转中...');
						vue.$parent.conName="Main";
					}else{
						Toast.fail('原密码不正确');
					}
				}).catch(function(err0r){
					console.log(error)
				})
			}
			
		}
	}
}
</script>

<style>
	.replying{
		display: flex;
		flex-direction:column;
		align-items: center;
		position: absolute;
		left: 50%;
		top: 50%;
		transform: translate(-50%,-50%);
		font-size: 12px;
	}
	.replyimg{
		margin-bottom: 10px;
	}
	.replyform{
		width: 90vw;
	}	
</style>>