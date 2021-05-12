$(window).on('load',function(){
    drawGrid('#chart-2');
    drawGrid('#chart-1');
    drawGrid('#chart-5');
    drawGrid('#chart-6');
    drawCircle('#chart-3',1,parseInt(bestRating),'#circle-1');
    drawCircle('#chart-4',2,parseInt(wrostRating),'#circle-2');
    drawLineGraph('#chart-1', chart_1_y, '#graph-1-container', 1);
    drawLineGraph('#chart-2', chart_2_y, '#graph-2-container', 2);
    drawLineGraph('#chart-5', chart_5_y, '#graph-5-container', 5);
    drawLineGraph('#chart-6', chart_6_y, '#graph-6-container', 6);

console.clear();

const menuDrawer = document.getElementById("menuDrawer");
const menuBtn = document.getElementById("menuBtn");
const menuIcon = document.getElementById("menuIcon");
const contentVeil = document.getElementById("contentVeil");
const menuItems = document.querySelectorAll(".menu-item");

const drawerTl = new TimelineLite({ paused: true });

const toggleVeil = () => TweenLite.set( contentVeil, {
  autoAlpha: drawerTl.reversed() ? 0 : 0.25
});

drawerTl
  .call(toggleVeil)
  .to( menuDrawer, 0.25, {
    x: 0, ease: Power1.easeOut
  })
  .to( menuBtn, 0.25, {
    x: 170, ease: Power1.easeOut
  }, 0)
  .reverse();

const toggleDrawer = () => {
  drawerTl.reversed( !drawerTl.reversed() );
  menuIcon.classList.toggle("fa-times");
}

menuBtn.onclick = toggleDrawer;

contentVeil.onclick = toggleDrawer;

menuItems.forEach( e => {
  e.onclick = toggleDrawer;
})
stockCpSpRating();
});

var chart_h = 40;
var chart_w = 80;
var stepX = 77 / 14;



var chart_1_y=[];
var chart_2_y=[];
var chart_5_y=[];
var chart_6_y=[];
var netStock=0;
var netCp=0;
var netSp=0;
var bestRating=0.0;
var wrostRating=100.0;
var bestModel;
var wrostModel;
var totalSold=0;
var highestSold=0;
var modelHighestSold;

function add1(val)
{
	chart_1_y.push(val);
	netStock=netStock+val;
}
function add2(val)
{
	chart_2_y.push(val);
	netCp=netCp+val;
}
function add5(val)
{
	chart_5_y.push(val);
	netSp=netSp+val;
}
function add6(val,model)
{
	chart_6_y.push(val);
	totalSold=totalSold+val;
	if(val>highestSold){
	highestSold=val;
	modelHighestSold=model;
	}
}


function stockCpSpRating()
{
	document.getElementById("netStock").innerHTML = "NetStock=" + netStock;
	document.getElementById("netCp").innerHTML = "NetStockCp=" + "₹"+netCp.toFixed(4)+"K";
	document.getElementById("netSp").innerHTML = "NetStockSp=" + "₹"+netSp.toFixed(4)+"K";
	document.getElementById("bestRated").innerHTML = "Model="+bestModel;
	document.getElementById("wrostRated").innerHTML = "Model="+wrostModel;
	document.getElementById("netSold").innerHTML = "NetSold=" + totalSold;
}
function check(val,model)
{
	if(val*20>bestRating){
		 bestModel=model;
		 bestRating=(val*20).toFixed(2);;
	}
	  
	 if(val*20<wrostRating){
		wrostModel=model;
		wrostRating=(val*20).toFixed(2);;
	}
	 
}

function Rated(){
	
}











function point(x, y) {
    x: 0;
    y: 0;
}
/* DRAW GRID */
function drawGrid(graph) {
    var graph = Snap(graph);
    var g = graph.g();
    g.attr('id', 'grid');
    for (i = 0; i <= stepX + 2; i++) {
        var horizontalLine = graph.path(
            "M" + 0 + "," + stepX * i + " " +
            "L" + 77 + "," + stepX * i);
        horizontalLine.attr('class', 'horizontal');
        g.add(horizontalLine);
    };
    for (i = 0; i <= 14; i++) {
        var horizontalLine = graph.path(
            "M" + stepX * i + "," + 38.7 + " " +
            "L" + stepX * i + "," + 0)
        horizontalLine.attr('class', 'vertical');
        g.add(horizontalLine);
    };
}


function drawLineGraph(graph, points, container, id) {


    var graph = Snap(graph);


    /*END DRAW GRID*/

    /* PARSE POINTS */
    var myPoints = [];
    var shadowPoints = [];

    function parseData(points) {
        for (i = 0; i < points.length; i++) {
            var p = new point();
            var pv = points[i] / 100 * 40;
            p.x = 83.7 / points.length * i + 1;
            p.y = 40 - pv;
            if (p.x > 78) {
                p.x = 78;
            }
            myPoints.push(p);
        }
    }

    var segments = [];

    function createSegments(p_array) {
        for (i = 0; i < p_array.length; i++) {
            var seg = "L" + p_array[i].x + "," + p_array[i].y;
            if (i === 0) {
                seg = "M" + p_array[i].x + "," + p_array[i].y;
            }
            segments.push(seg);
        }
    }

    function joinLine(segments_array, id) {
        var line = segments_array.join(" ");
        var line = graph.path(line);
        line.attr('id', 'graph-' + id);
        var lineLength = line.getTotalLength();

        line.attr({
            'stroke-dasharray': lineLength,
                'stroke-dashoffset': lineLength
        });
    }

    function calculatePercentage(points, graph) {
        var initValue = points[0];
        var endValue = points[points.length - 1];
        var sum = endValue - initValue;
        var prefix;
        var percentageGain;
        var stepCount = 1300 / sum;

        function findPrefix() {
            if (sum > 0) {
                prefix = "+";
            } else {
                prefix = "";
            }
        }

        var percentagePrefix = "";

        function percentageChange() {
            percentageGain = initValue / endValue * 100;
            
            if(percentageGain > 100){
              console.log('over100');
              percentageGain = Math.round(percentageGain * 100*10) / 100;
            }else if(percentageGain < 100){
              console.log('under100');
              percentageGain = Math.round(percentageGain * 10) / 10;
            }
            if (initValue > endValue) {
              
                percentageGain = endValue/initValue*100-100;
                percentageGain = percentageGain.toFixed(2);
              
                percentagePrefix = "";
                $(graph).find('.percentage-value').addClass('negative');
            } else {
                percentagePrefix = "+";
            }
          if(endValue > initValue){
              percentageGain = endValue/initValue*100;
              percentageGain = Math.round(percentageGain);
          }
        };
        percentageChange();
        findPrefix();

        var percentage = $(graph).find('.percentage-value');
        var totalGain = $(graph).find('.total-gain');
        var hVal = $(graph).find('.h-value');

        function count(graph, sum) {
            var totalGain = $(graph).find('.total-gain');
            var i = 0;
            var time = 1300;
            var intervalTime = Math.abs(time / sum);
            var timerID = 0;
            if (sum > 0) {
                var timerID = setInterval(function () {
                    i++;
                    
                    if (i === sum) clearInterval(timerID);
                }, intervalTime);
            } else if (sum < 0) {
                var timerID = setInterval(function () {
                    i--;
                  
                    if (i === sum) clearInterval(timerID);
                }, intervalTime);
            }
        }
        count(graph, sum);

        percentage.text(percentagePrefix + percentageGain + "%");
        
        setTimeout(function () {
            percentage.addClass('visible');
            hVal.addClass('visible');
        }, 1300);

    }


    function showValues() {
        var val1 = $(graph).find('.h-value');
        var val2 = $(graph).find('.percentage-value');
        var val5 = $(graph).find('.percentage-value');
        var val6 = $(graph).find('.h-value');
        val1.addClass('visible');
        val2.addClass('visible');
        val5.addClass('visible');
        val6.addClass('visible');
    }

    function drawPolygon(segments, id) {
        var lastel = segments[segments.length - 1];
        var polySeg = segments.slice();
        polySeg.push([78, 38.4], [1, 38.4]);
        var polyLine = polySeg.join(' ').toString();
        var replacedString = polyLine.replace(/L/g, '').replace(/M/g, "");

        var poly = graph.polygon(replacedString);
        var clip = graph.rect(-80, 0, 80, 40);
        poly.attr({
            'id': 'poly-' + id,
            /*'clipPath':'url(#clip)'*/
                'clipPath': clip
        });
        clip.animate({
            transform: 't80,0'
        }, 1300, mina.linear);
    }

      parseData(points);
      
      createSegments(myPoints);
      calculatePercentage(points, container);
      joinLine(segments,id);
 
      drawPolygon(segments, id);
    

    $('#poly-'+id).attr('class','show');

     function drawPolygon(segments,id){
      var polySeg = segments;
      polySeg.push([80,40],[0,40]);
      var polyLine = segments.join(' ').toString();
      var replacedString = polyLine.replace(/L/g,'').replace(/M/g,"");
      var poly = graph.polygon(replacedString);
      poly.attr('id','poly-'+id)
    }
    drawPolygon(segments,id);
}
function drawCircle(container,id,progress,parent){
  var paper = Snap(container);
  var prog = paper.path("M5,50 A45,45,0 1 1 95,50 A45,45,0 1 1 5,50");
  var lineL = prog.getTotalLength();
  var oneUnit = lineL/100;
  var toOffset = lineL - oneUnit * progress;
  var myID = 'circle-graph-'+id;
  prog.attr({
    'stroke-dashoffset':lineL,
    'stroke-dasharray':lineL,
    'id':myID
  });
  
  var animTime = 1300/*progress / 100*/
  
  prog.animate({
    'stroke-dashoffset':toOffset
  },animTime,mina.easein);
  
  function countCircle(animtime,parent,progress){
    var textContainer = $(parent).find('.circle-percentage');
    var i = 0;
    var time = 1300;
    var intervalTime = Math.abs(time / progress);
    var timerID = setInterval(function () {
      i++;
      textContainer.text(i+"%");
      if (i === progress) clearInterval(timerID);
    }, intervalTime);           
  }
  countCircle(animTime,parent,progress);
}