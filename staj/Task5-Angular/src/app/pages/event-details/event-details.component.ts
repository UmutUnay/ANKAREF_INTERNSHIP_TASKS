import {Component, OnInit} from '@angular/core';
import {CommonModule} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-event-details',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './event-details.component.html',
  styleUrl: './event-details.component.scss'
})
export class EventDetailsComponent implements OnInit {
  event: any;
  attendingUsers: any[] = [];

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    const eventId = this.route.snapshot.paramMap.get('id');
    this.fetchEventDetails(eventId);
    this.fetchAttendingUsers(eventId);
  }

  fetchEventDetails(eventId: string | null): void {
    if (eventId) {
      this.http.get<any>(`http://localhost:8080/api/event/${eventId}`).subscribe(data => {
        this.event = data;
      });
    }
  }

  fetchAttendingUsers(eventId: string | null): void {
    if (eventId) {
      this.http.get<any[]>(`http://localhost:8080/api/event/get-attendees-${eventId}`).subscribe(data => {
        this.attendingUsers = data;
      });
    }
  }
}
