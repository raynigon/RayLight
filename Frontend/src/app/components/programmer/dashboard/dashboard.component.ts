import { Component, OnInit } from '@angular/core';
import { ControlPanelService } from 'src/app/services/ControlPanelService';

@Component({
  selector: 'app-programmer-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  public panels: any[];

  constructor(private controlpanel: ControlPanelService) {}

  async ngOnInit() {
    this.panels = await this.controlpanel.listAllControlPanels();
  }

  async create() {
    const name = prompt('Please enter a name', 'Harry Potter');
    const result = await this.controlpanel.createControlPanel(name, {});
    console.log(result);
    this.ngOnInit();
  }
}
