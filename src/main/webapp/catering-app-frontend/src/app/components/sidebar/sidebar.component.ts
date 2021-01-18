import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }


  // toHome() {
  //   document.ready(function () {
  //     $('#sidebarCollapse').on('click', function () {
  //       $('#sidebar').toggleClass('active');
  //     });
  //   });
  //
  //   document.getElementById('masthead').scrollIntoView({behavior: 'smooth'});
  // }

}
