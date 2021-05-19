$(document).ready(function() {
var piesiteFired = 0;


$(document).ready(function() {
    var $win = $(window),
        $win_height = $(window).height(),
        // - A multiple of viewport height - The higher this number the sooner triggered.
        windowPercentage = $(window).height() * 0.9;
    $win.on("scroll", scrollReveal);
    function scrollReveal() {
        var scrolled = $win.scrollTop();
        
        ///////////////////////////////////////
        // Bar Charts scroll activate, looking for .trigger class to fire.
        $(".trigger").each(function() {
            var $this = $(this),
                offsetTop = $this.offset().top;
            if (
                scrolled + windowPercentage > offsetTop ||
                $win_height > offsetTop
            ) {
                $(this).each(function(key, bar) {
                    var percentage = $(this).data("percentage");
                    $(this).css("height", percentage + "%"); 
                    
                    ///////////////////////////////////////
                    //        Animated numbers
                    $(this).prop("Counter", 0).animate(
                        {
                            Counter: $(this).data("percentage")
                        },
                        {
                            duration: 2000,
                            easing: "swing",
                            step: function(now) {
                                $(this).text(Math.ceil(now));
                            }
                        }
                    );
                    //        Animated numbers
                    ///////////////////////////////////////
                });

            } else {
                ///////////////////////////////////////
                // To keep them triggered, lose this block.
                $(this).each(function(key, bar) {
                    $(this).css("height", 0);
                });
            }    
            
        });

    }
    scrollReveal();
});
})
var colors=["bar greenBar trigger","bar blueBar trigger","bar orangeBar trigger","bar purpleBar trigger"];
var x=0;
	function addUnits(val){
		document.getElementById("bar").setAttribute("data-percentage", val);
		if(x>3){
			x=0;
			document.getElementById("bar").setAttribute("class",colors[x]);
		}
		else{
			document.getElementById("bar").setAttribute("class",colors[x]);
		x++;
		}

		
		document.getElementById("bar").setAttribute("id","1");
		
	}
	