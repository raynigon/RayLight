import { Component, OnInit } from '@angular/core';
import { ControlPanelService } from 'src/app/services/ControlPanelService';

@Component({
  selector: 'app-programmer-container',
  templateUrl: './programmer-container.component.html',
  styleUrls: ['./programmer-container.component.scss']
})
export class ProgrammerContainerComponent implements OnInit {
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

  public openDashboardSettings() {
    throw new Error('Not Implemented Open Dashboard Settings Action');
  }

  public openDashboard(dashboard: any) {
    throw new Error('Not Implemented Open Dashboard Action');
  }
}
