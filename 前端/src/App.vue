<template>  <div id="app">
	 <!-- <keep-alive> -->
		  <component v-bind:is="conName"></component>
	  <!-- </keep-alive> -->  </div></template><script>import Login from "./components/Login.vue"import Main from "./components/Main.vue"import Register from "./components/Register.vue"import Cookies from 'js-cookie'
import AddAlbum from './components/AddAlbum.vue'
import Reply from './components/Reply.vue'
import Photo from './components/Photo.vue'
import DelAlbum from './components/DelAlbum.vue'
// import My 	from './components/My.vue';export default {  name: 'app',  data:function(){	  return{
		  username:"",		  conName:""  ,
		  token:"",
		  albums:[],
		  album_click:[],
		  album_name:""	  }    },
  beforeMount:function(){
  	//读取cookie看是否已经登入
	let um = Cookies.get("username");
	let tk= Cookies.get("token");
	let ab= Cookies.get("albums");
	console.log(ab);
	if(um!=undefined&&tk!=undefined){
		this.username=um;
		this.token=tk;
		if(ab!=""){
			var abObj = JSON.parse(ab);
			this.albums=abObj;
			console.log("ab",ab);
			console.log("ablbums",this.albums);
		}
		
	}
	
  },
  mounted:function(){
  	this.conName=this.username==""?"Login":"Main";
  },
  components: {
  	  Login:Login,
  	  Register:Register,
  	  Main:Main,
	  AddAlbum:AddAlbum,
	  Reply:Reply,
	  Photo:Photo,
	  DelAlbum:DelAlbum
	  // My:My
    }}
</script><style>
</style>