<template>
	<div>
		<div id="big">
			<div id="mid">
				
				
					<component v-bind:is="comName" > </component>
				
				
			</div>
			<div id="sml">
				
				<div id="three">
					<span><img @click="fun3()" id="img3" src="../static/xitongshezhi.png" ></span>
				</div>
				<div id="two">
					<span><img @click="fun2()" id="img2" src="../static/baocuo.png" ></span>
				</div>
				
				<div id="one"  >
					<span><img @click="fun1()" id="img1" src="../static/zhanghao.png" ></span>
				</div>
				
			</div>
		</div>
	</div>
	
	
</template>

<script>
	import Oner from "./Oner.vue"
	import Twer from "./Twer.vue"
	import Threer from "./Threer.vue"
export default{
		name:"Assignment",
		data:function(){
			return{
				comName:"Oner",
				
				
			}
		},
		methods:{
			fun1:function(){
				this.comName="Oner"
			
			},
			fun2:function(){
				this.comName="Twer"
				
			},
			fun3:function(){
				this.comName="Threer"
				
			},
		},
		components:{
			Oner:Oner,
			Twer:Twer,
			Threer:Threer
		}
	}
</script>

<style>
		#big{
			width: 300px;
			height: 500px;
			border-radius:5px;
			border: 1px solid black;
			margin: auto;
			
		}
		#mid{
			width: 300px;
			height: 450px;
			
		}
		#sml{
			width: 300px;
			height: 50px;
			
		}
		#one{
			width: 99px;
			height: 50px;
			float: left;
			
			border: 1px solid black;
			
		}
		#two{
			width: 96px;
			height: 50px;
			float: left;
			
			border: 1px solid black;
			
		}
		#three{
			width: 99px;
			height: 50px;
			float: left;
		
			border: 1px solid black;
			
		}
		#img1{
			width: 50px;
			height: 50px;
			text-align: center;
			margin-left: 25px;
		}
		#img2{
			width: 50px;
			height: 50px;
			text-align: center;
			margin-left: 25px;
		}
		#img3{
			width: 50px;
			height: 50px;
			text-align: center;
			margin-left: 25px;
		}
</style>
