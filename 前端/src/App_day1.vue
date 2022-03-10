<template>
	<!-- 必须放个div作为容器不能直接放 -->
  <div id="app">
	<!-- <div v-bind:title="titlename">w s div</div>
    姓名:<input v-model="name" /><br>
	年龄:<input v-model="age" /><br>
	<button type="button" @click="add()">add</button><br />
	<ul>
		<li v-for="stu in stus">{{stu.name}}:{{stu.age}}</li>
	</ul> -->
	<!-- 调用组件 -->
	<!-- <Header></Header>
	<Footer></Footer> -->
	<!-- 动态组件 -->
<!-- 	<button type="button" @click="comName='Header'">header</button>
	<button type="button" @click="comName='Footer'">footer</button>
	<input v-model="id">
	  <el-button @click="myget()" type="danger" >危险按钮</el-button>
	 <div>{{myname}}</div> -->
	<!-- <Assignment></Assignment> -->
	<!-- <input type="file" name="" id="file" />
	<button type="button" @click="upload()">shangchuan</button> -->
	
	<mt-navbar v-model="selected">
	  <mt-tab-item id="1">选项一</mt-tab-item>
	  <mt-tab-item id="2">选项二</mt-tab-item>
	  <mt-tab-item id="3">选项三</mt-tab-item>
	</mt-navbar>
	
	<!-- tab-container -->
	<mt-tab-container v-model="selected">
	  <mt-tab-container-item id="1">
	    <mt-cell v-for="n in 10" :title="'内容 ' + n" />
	  </mt-tab-container-item>
	  <mt-tab-container-item id="2">
	    <mt-cell v-for="n in 4" :title="'测试 ' + n" />
	  </mt-tab-container-item>
	  <mt-tab-container-item id="3">
	    <mt-cell v-for="n in 6" :title="'选项 ' + n" />
	  </mt-tab-container-item>
	</mt-tab-container>
	</div> 
	
		
	

</template>

<script>
	// import Header from "./components/Header.vue"
	// import Footer from "./components/Footer.vue"
	// import Assignment from "./components/Assignment.vue"
	// const qiniu = require('qiniu-js')
	 

export default{
	name:"app",
	data:function(){
		return{
			selected:"2"
			
		}
	},
	
	methods:{
		// 
		upload:function(){
			//获取文件
			let file = document.querySelector("#file").files[0];
			//获取token 服务端拿
			let vue = this;
			this.$root.axios.get('/server/get_token', {
			    params: {
			      
			    }
			  })
			  .then(function (response) {
			    console.log(response);
				let token = response.data;
				const observer = {
				  next(res){
				    // 上端进度
					console.log(res);
				  },
				  error(err){
				    // 上传错误
					console.log(err);
				  },
				  complete(res){
				    // 上传完成
					console.log(res);
				  }
				}
				let putExtra={};
				let config={};
				const observable = qiniu.upload(file, null, token,putExtra, config)
				const subscription = observable.subscribe(observer) // 上传开始
			  })
			  .catch(function (error) {
			    console.log(error);
			  })
			  .then(function () {
			    // always executed
			  });  
			//准备上传文件
			
		},
		m1:function(){
			this.names.push(this.msg)
			this.ages.push(this.age)
			this.msg=""
			this.age=""
		},
		add:function(){
			let obj={
				age:this.age,
				name:this.name,
			}
			this.stus.push(obj);
			this.msg="";
			this.age="";
		},
		fun1:function(){
			this.comName="Oner"
		},
		fun2:function(){
			this.comName="Twer"
		},
		fun3:function(){
			this.comName="Threer"
		},
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
		}
	},
	//注册组件 k-v
	components:{
		// Header:Header,
		// Footer:Footer,
		// Assignment:Assignment,
		

	}
}
</script>

<style>


</style>
