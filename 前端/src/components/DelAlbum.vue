<template>
	<div class="delAlbum">
		<van-nav-bar title="删除相册" >
		</van-nav-bar>
		<select v-model="selectedAlbum" class="targetalbum">
			<option v-for="a in $root.$children[0].albums" v-model="album">
				{{a.name}}
			</option>
			
		</select>
		<mt-button type="defualt" size="normal"  @click="goback()" class="btn">返回</mt-button>
		<mt-button type="primary" size="normal" @click="delAlbum()" class="btn">确定删除</mt-button>
		<div id="imgs" ></div>
	</div>
</template>

<script>
	import { Dialog,Toast } from 'vant';
	import Cookies from 'js-cookie';
	export default{
			name:"DelAlbum",
			data:function(){
				return{
					selectedAlbum:"",
					album:""
				}
			},
			methods:{
				goback:function(){
					this.$root.$children[0].conName="Main";
				},
				delAlbum:function(){
					var vue=this;
					Dialog.confirm({
					  title: '警告',
					  message: '是否确认删除',
					})
					  .then(() => {
					    // on confirm
						vue.$root.axios.get("/server/user/deleteAlbum",{
										 params:{
											 username:vue.$root.$children[0].username,
											 albumName:vue.selectedAlbum
											 
										 }
						}).then(function(res){
										 if(res.data.code==101){
											Toast.success("删除成功!");
										 }else{
											 Toast.fail("删除失败!");
										 }
						}).catch(function(error){
								 console.log(error)
						});
						
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
						});
					  })
					  .catch(() => {
					    // on cancel
					  });
					
					
					// console.log(this.selectedAlbum);
					// console.log(this.$root.$children[0].username);
					
					
					
				}
				
			}
	}
	
</script>

<style>
	.targetalbum{
		width: 100%;
		background:#FAFAFA;
		height: 30px;
		margin-top: 50px;
	}
	.delAlbum{		margin-top: 6px;	}	#file{		position: absolute;		left: -1000px;	}	.btn{		margin-top: 6px;		margin-bottom: 6px;		margin-left: 15%;			}
</style>
