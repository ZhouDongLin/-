<template>
	<div class= "albums">
		<div v-for="a in $root.$children[0].albums" class="album">
			<!-- 封面 -->
			<div class="surface">
				<img :src='a.photos[0].url'>
			</div>
			<!-- 相册名 -->
			<div class="title">
				{{a.name}}
			</div>
		</div>
	</div>
</template>

<script>
	export default{
		name:"Albums"
	}
</script>

<style>
	.albums{
		margin-top: 6px;
	}
	.album{
		width: 46%;
		height: 180px;
		float: left;	
		border: 1px solid lightgray;
		border-radius: 20px;
	}
	.surface{
		width: 96%%;
		margin-left: 2%;
		height: 150px;
		overflow: hidden;
		
	}
	.surface img{
		width: 100%;
	}
	.title{
		text-align: center;
		height: 200px;
		line-height: 20px;
	}
</style>
