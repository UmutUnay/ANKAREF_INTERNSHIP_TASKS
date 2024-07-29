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
  templateUrl: './user-details.component.html',
  styleUrl: './user-details.component.scss'
})
export class UserDetailsComponent implements OnInit {
  user: any;
  events: any[] = [];

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit(): void {
    const userId = this.route.snapshot.paramMap.get('id');
    this.fetchUserDetails(userId);
    this.fetchEvents(userId);
  }

  // FIX THE LINK, IT SHOULD BE http://localhost:8080/auth/user/${userId} THIS LINK IS FOR TESTING
  fetchUserDetails(userId: string | null): void {
    if (userId) {
      this.http.get<any>(`http://localhost:8080/auth/user-get/${userId}`).subscribe(data => {
        this.user = data;
      });
    }
  }

  fetchEvents(userId: string | null): void {
    if (userId) {
      this.http.get<any[]>(`http://localhost:8080/auth/user/${userId}-get-attending-events`).subscribe(data => {
        this.events = data;
      });
    }
  }
}
