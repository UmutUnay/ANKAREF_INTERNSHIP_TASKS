import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-all-events',
  standalone: true,
  imports: [],
  templateUrl: './all-events.component.html',
  styleUrl: './all-events.component.scss'
})
export class AllEventsComponent implements OnInit {
  events: any[] = [];

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.fetchEvents();
  }

  fetchEvents(): void {
    this.http.get<any[]>('http://localhost:8080/api/event/all-events').subscribe(data => {
      this.events = data;
    });
  }

  viewEventDetails(eventId: number): void {
    this.router.navigate([`/event-details/${eventId}`]);
  }

  attendEvent(eventId: number): void {
    this.http.post('http://localhost:8080/api/event/attend-event', { eventId }).subscribe(response => {
      alert('You have been added to the event!');
    });
  }
}
