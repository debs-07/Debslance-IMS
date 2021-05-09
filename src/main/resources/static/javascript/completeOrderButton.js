window.onload = function(){ 
$('.order').click(function(e) {

    let button = $(this);

    if(!button.hasClass('animate')) {
        button.addClass('animate');
        setTimeout(() => {
            button.removeClass('animate');
        }, 10000);
    }
setTimeout(function() {
	const ID=parseInt(id);
   window.location.href = `/user/modifyStock/${ID}`;
   console.log("hello"); 
  }, 7000);
});
};