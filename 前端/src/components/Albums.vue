<template>	<div class="albums">
	<van-pull-refresh :head-height="80" >		<van-nav-bar title="相册" >		  <template #right>
			<van-icon @click="toDelAlbum()" name="delete-o" size="20" />		    <van-icon @click="toAddAlbum()" name="plus" size="20" />		  </template>		</van-nav-bar>
		
			<div v-for="a in $root.$children[0].albums" class="album">
				<!-- 封面 -->
				<div class="img" @click="goPhoto(a)">
					<img v-if="a.photos.length!=0" :src="a.photos[0].url" />
					<img  v-else src="https://s4.ax1x.com/2021/12/27/T0y1Ve.png" />
				</div>
				<!-- 相册名 -->
				<div class="title">{{a.name}}</div>
			</div>	</van-pull-refresh>
	<div class = "test"></div>	</div>	</template><script>import { Button, Icon } from 'vant';import Vue from 'vue';import { NavBar } from 'vant';Vue.use(NavBar);export default{	name:"Albums",
	data(){
		return{
			
		}
	},
	methods:{
		toAddAlbum:function(){
			this.$root.$children[0].conName="AddAlbum"
		},
		toDelAlbum:function(){
			this.$root.$children[0].conName="DelAlbum";
		},
		goPhoto:function(a){
			console.log("a",a);
			this.$root.$children[0].album_name=a.name;
			 this.$root.$children[0].album_click=a.photos;
			 console.log("a.photos",a.photos);
			this.$root.$children[0].conName="Photo"
		}
	}}</script><style>	.albums{		margin-top: 6px;	}
	.test{
		height: 56px;
		overflow: auto;
	}	.album{		width: 46%;		height: 210px;		float: left;		border: 1px solid lightgrey;		border-radius: 20px;		margin: 1.5%;	}	.img{		width:96%;		margin: 2%;		height: 180px;		border-radius: 20px;		overflow: hidden;			}	.img img{		width: 100%;	}	.title{		margin-top: 5px;		text-align: center;		height: 20px;		line-height: 20px;		overflow: hidden;	}
	</style>