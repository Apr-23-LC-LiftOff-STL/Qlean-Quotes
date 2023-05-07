import {Component, ElementRef, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {User} from "@core/entities";
import {ActivatedRoute, Router} from "@angular/router";
import {CustomerService} from "@core/services/customer.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.page.html',
  styleUrls: ['./dashboard.page.scss']
})
export class DashboardPage implements OnInit {

  customer: User = new User();
  subscriptions: Subscription[] = [];

  ngOnInit(): void {
    this.subscriptions.push(this._customerService.getCustomer().subscribe({
      next: (data) => {
        console.log('Customer retrieved:', data);
        this.customer = new User(data);
      }, error: () => {
        console.log('Failed to get Customer');
      }
    }));
  }

  constructor(private _router: ActivatedRoute, private _route: Router, private _customerService: CustomerService, private _activatedRoute: ActivatedRoute,
              private el: ElementRef) {
  }

}
