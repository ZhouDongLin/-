<template>
	<div class="upload">
		<van-nav-bar title="照片上传" >
		</van-nav-bar>
		<select @change="cleanOld()" v-model="selectedAlbum" class="targetalbum">
			<option  v-for="a in $root.$children[0].albums">{{a.name}}</option>			
		</select>
		<input type="file" multiple="multiple" id="file" @change="showImg()">
		<mt-button type="default" size="normal" @click="chooseFile()" class="btn">选择文件</mt-button>
		<mt-button type="primary" size="normal" @click="upload()" class="btn">开始上传</mt-button>
		<van-pull-refresh :head-height="80" >
			<div id="imgs" ></div>
		</van-pull-refresh>
		<div class = "test1"></div>
		
	</div>
</template>

<script>
	const qiniu = require('qiniu-js');
	import Cookies from 'js-cookie';
	import {Toast} from 'vant';
	export default{
		name:"Upload",
		data:function(){
			  return{
				  username:"",
				  conName:""  ,
				  selectedAlbum:"默认相册"
			
			  }  
		},
		methods:{
			upload:function(){
				//获取所有的照片
				var vm = this
				let files = document.querySelector("#file").files;
				console.log(files);
				//获取上传文件的token
				this.$root.axios.get("/server/user/getToken",{
					params:{
						token:vm.$root.$children[0].token
					}
				}).then(function(res){
					console.log(res)
					if(res.data.code==101){
						let qntoken=res.data.msg
						vm.uploadFiles(files,qntoken);
					}else{
						console.log("error token")
					}
				}).catch(function(err){
					console.log(err)
				});
				
				
				
			},
			uploadFiles:function(files,qntoken){
				let vm = this;
				for(let i=0;i<files.length;i++){
					let file=files[i];
					const observer = {
									  next(res){
									    // 上端进度
										console.log(res);
										let div = document.querySelector(".img"+i);
										div.style.width=res.total.percent+"%";
									  },
									  error(err){
									    // 上传错误
										console.log(err);
									  },
									  complete(res){
									    // 上传完成
										let key = res.key;
										let name = file.name;
										let album=vm.selectedAlbum;
										let token = vm.$root.$children[0].token;
										console.log("上传成功",res);
										console.log(vm.selectedAlbum);
										vm.$root.axios.get("/server/user/upload",{
											params:{
												key:key,
												name:name,
												album:album,
												token:token
											}
										}).then(function(res){
											console.log(res)
										}).catch(function(err){
											console.log(err)
										});
										
										
										
										
									  }
									}
									let putExtra={};
									let config={};
									const observable = qiniu.upload(file, null, qntoken,putExtra, config)
									const subscription = observable.subscribe(observer) // 上传开始
									//刷新
									vm.$root.axios.get("/server/user/refresh",{
										params:{
											username:vm.$root.$children[0].username
										}
									}).then(function(res){
										vm.$root.$children[0].albums=res.data.data.albums;
										var albumsStr=JSON.stringify(res.data.data.albums);
										Cookies.set("albums",albumsStr);
										console.log(res)
									}).catch(function(err){
										console.log(err)
									});
					}
				},
				showImg:function(){
					let imgs=document.querySelector("#imgs");
					let files=document.querySelector("#file").files;
					
					for(var i=0 ; i<files.length ;i++){
						let file =files[i];
						if(!file.name.endsWith("jpg") && !file.name.endsWith("png") 
						&& !file.name.endsWith("gif")&&!file.name.endsWith("GIF")&&
						!file.name.endsWith("JPG")&&!file.name.endsWith("PNG")){
							Toast.fail("请选择正确的照片格式!");
							}else{
								let img =document.createElement("img");
								let fileReader=new 	FileReader();
								fileReader.onload=function(){
									img.src=fileReader.result;
									img.style.width="100%";
									//img.style.marginTop="10px";
									
								}
								fileReader.readAsDataURL(file);
								//创建一个div
								let div=document.createElement("div");
								div.setAttribute("id","mydiv");
								div.style.height="180px";
								div.style.width="48%";
								div.style.float="left";
								div.style.marginLeft="4px";
								div.style.marginTop="10px";
								div.style.overflow="hidden";
								//进度条
								let progress=document.createElement("div");
								progress.setAttribute("class","jdt");
								progress.className="img"+i;
								progress.style.backgroundColor="green";
								progress.style.width="0%";
								progress.style.height="4px";
								progress.style.float="left";
								//进度放进div中
								div.appendChild(progress);
								//照片放进div中
								div.appendChild(img);
								//把div存入imgs
								imgs.appendChild(div);
							}
						
					
					}
				},
				chooseFile:function(){
					document.querySelector("#file").click();	
					
				},
				cleanOld:function(){
					var a=document.querySelector("#imgs");
					//a.remove();
					//while()
						while(a.hasChildNodes()) //当div下还存在子节点时 循环继续
						{
						 a.removeChild(a.firstChild);
						}			
				}
			}
		}
	
</script>

<style>
	.targetalbum{
		width: 100%;
		background:#FAFAFA;
		height: 30px;
		margin-top: 20px;
		margin-bottom: 20px;
	}
	.upload{
		margin-top: 6px;
	}
	#file{
		position: absolute;
		left: -1000px;
	}
	.btn{
		margin-top: 6px;
		margin-bottom: 6px;
		margin-left: 15%;
		
	}
	.test1{
		height: 56px;
		overflow: auto;
	}
	
</style>
