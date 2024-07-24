import {Component, HostListener, OnDestroy, OnInit} from '@angular/core';
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [
    RouterOutlet
  ],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss'
})
export class LayoutComponent implements OnInit, OnDestroy
{
  // OnInit and OnDestroy methods
  ngOnInit(){}
  ngOnDestroy(){}

}
