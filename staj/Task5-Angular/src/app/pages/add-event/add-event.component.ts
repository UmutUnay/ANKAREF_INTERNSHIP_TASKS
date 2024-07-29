import {Component, ElementRef, HostListener, OnDestroy, OnInit, Renderer2} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {Router, RouterModule} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-add-event',
  standalone: true,
  imports: [
    FormsModule,
    RouterModule
  ],
  templateUrl: './add-event.component.html',
  styleUrl: './add-event.component.scss'
})
export class AddEventComponent implements OnInit{

  eventObject: Event;

  constructor(private http: HttpClient, private router: Router)
  {
    this.eventObject = new Event();
  }

  ngOnInit(): void {}

    onAddEvent() {
    this.http.post('http://localhost:8080/api/events/add-event', this.eventObject).subscribe((res:any) => {
      if (res.success) {
        alert('Event added successfully');
        this.router.navigate(['/all-events']);
      }
    });
  }}

class Event {
  name: string;
  description: string;
  date: string;

  constructor() {
    this.name = '';
    this.description = '';
    this.date = '';
  }
}
