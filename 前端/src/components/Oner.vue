<template>
	<div>
		<div v-if="this.myname == ''">
		login:<input v-model="id"/>
		<el-button @click="myget()" type="danger" >login</el-button>
		</div>
		<div v-else>{{this.myname}}
		<el-button @click="logout()" type="danger" >logout</el-button>
		</div>
	</div>
	
</template>

<script>
	export default{
		name:"Oner",
		data:function(){
			return{
				
				id:"",
				myname:''
			
				
			}
		},				
		methods:{
			myget:function(){
					let vue = this;
					this.$root.axios.get('/server/get', {
					    params: {
					      id: this.id
					    }
					  })
					  .then(function (response) {
					    console.log(response);
						vue.myname = response.data;
					  })
					  .catch(function (error) {
					    console.log(error);
					  })
					  .then(function () {
					    // always executed
					  });  
				},
				logout:function(){
					this.myname=''
					this.id=''
				}
			}
		}
	
</script>

<style>
</style>
