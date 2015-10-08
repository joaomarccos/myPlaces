<div class="panel panel-default">            
	<div class="panel-body corpopub">   
		<small id="post-result"></small>
		
		<div id="map-canvas"></div>        
		<input id="pac-input" class="form-control controls" type="text" name="place-description" placeholder="Informe um local">        
		<form id="newPost" method="post" action="newPost">                    
			<input  hidden id="place" name="place" type="text">
			<input  hidden id="lat" name="lat" type="text">
			<input  hidden id="lng" name="lng" type="text">            
			<textarea class="form-control content-post" name="post" cols="40" rows="5" placeholder="Diga algo sobre isso"></textarea>            
		</form>
	</div>
	<div class="panel-footer footer rodapepub">
		<button onclick="submitPost()" class="btn btn-success pull-right publicar">Postar</button>
	</div>
</div>