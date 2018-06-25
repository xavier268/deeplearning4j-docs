/*!
 * google-analytics-event-tracking
 * https://github.com/alligo/google-analytics-event-tracking 
 */
 window.GAET&&console.log("WARNING! GAET loaded more than once"),function(e,t){t.GAET=t.GAET||{},GAET.debug=t.GAETDebug||!1,GAET.maxwait=t.GAETMaxwait||1e3,GAET.version="v0.5.1",GAET.prepareEvents=function(){var t=e("[data-ga-event]");e.each(t,function(t,a){var n=e(this),o=e(this).data("ga-event"),i=e(this).data("ga-category")||"",l=e(this).data("ga-action")||"",r=e(this).data("ga-label")||"",g=e(this).data("ga-value")||void 0,E=e(this).data("ga-gaet")||!1;if(E)return GAET.debug&&console.log("window.GAET.prepareEvents skipping",n,E),!0;if(GAET.debug&&console.log("window.GAET.prepareEvents prepared",o,i,l,r,g,n),n.data("ga-gaet",1),"load"===o||"ready"===o)try{GAET.debug&&console.log("window.GAET.prepareEvents FIRED!",o,i,l,r,g,n),ga("send","event",i,l,r,g)}catch(c){console.log("GAET.prepareEvents Exception",c)}else e(this).on(o,function(e){if(GAET.debug&&console.log("window.GAET.prepareEvents FIRED!",o,i,l,r,g,n),"click"===o||"submit"===o)n.data("ga-done")||GAET.prepareEventWait(n,e,o,i,l,r,g);else try{ga("send","event",i,l,r,g)}catch(t){console.log("GAET.prepareEvents Exception",t)}})})},GAET.prepareEventWait=function(t,a,n,o,i,l,r){var g,E=!1;a.preventDefault(),g=function(){if(E||e(a).data("ga-done"))return!0;if(e(t).data("ga-done",1),E=!0,"click"===n){if("_blank"===a.target.getAttribute("target"))return a.target.click(),!0;document.location=e(t).prop("href")}else"submit"===n?e(t)[0].submit():console.log("GAET.prepareEventWait: evento desconhecido",t,n)},setTimeout(g,GAET.maxwait);try{ga("send",{hitType:"event",eventCategory:o,eventAction:i,eventLabel:l,eventValue:r||void 0},{hitCallback:g})}catch(c){console.log("GAET.prepareEventWait Exception",c)}},GAET.initAll=function(){e(document).ready(function(){ga?(GAET.debug&&console.log("GAET.initAll"),GAET.prepareEvents()):console.log("GAET: Google analytics.js (ga) not loaded. Aborting"),"undefined"!=typeof _gaq&&console.log("GAET: Old Google analytics (_gaq) loaded. Please use only new ga")})}}(jQuery,window),GAET.initAll();