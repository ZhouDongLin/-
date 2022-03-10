<template>
	<div>
		<mt-button plain type="primary" size="large" @click="logout()">登出</mt-button>
	</div>
</template>

<script>
	import Cookies from 'js-cookie'
	export default{
		name:"My",
		data:function(){
			  return{
				 
			  }
		},
		methods:{				
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
</style>
