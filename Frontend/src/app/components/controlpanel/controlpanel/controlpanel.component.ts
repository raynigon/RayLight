import { Component } from '@angular/core';
import { ControlPanelService } from 'src/app/services/ControlPanelService';
import { ActivatedRoute } from '@angular/router';
import { DMXService } from 'src/app/services/DMXService';
import { IPanel } from 'src/app/model/panels/IPanel';
import { IButtonPanel } from 'src/app/model/panels/IButtonPanel';
import { ISliderPanel } from 'src/app/model/panels/ISliderPanel';
import { MatDialog } from '@angular/material';
import { ButtonEditDialogComponent } from '../editdialogs/buttoneditdialog/buttoneditdialog.component';
import { SliderEditDialogComponent } from '../editdialogs/slidereditdialog/slidereditdialog.component';

const GRID_SIZE = 10;
const DELETE_KEYS = ['Backspace', 'Delete', 'del'];

@Component({
  selector: 'app-controlpanel',
  templateUrl: './controlpanel.component.html',
  styleUrls: ['./controlpanel.component.scss']
})
export class ControlPanelComponent {
  public editMode = false;

  public controlPanel: any;

  private dragInit: number[];

  constructor(
    private service: ControlPanelService,
    private route: ActivatedRoute,
    public dialog: MatDialog
  ) {
    this.load();
  }

  private async load() {
    this.controlPanel = await this.service.getById(
      this.route.snapshot.paramMap.get('id')
    );
  }

  public addPanel(type: 'button' | 'slider') {
    let panel = null;
    if (type === 'button') {
      panel = {
        type: 'button',
        dimensions: {
          x: 10,
          y: 10,
          width: 100,
          height: 100
        },
        styles: {
          backgroundActive: 'red',
          backgroundInactive: '',
          colorActive: 'blue',
          colorInactive: ''
        },
        action: {
          type: 'set-dmx-value',
          channel: 0,
          value: 0
        },
        description: ''
      } as IButtonPanel;
    } else if (type === 'slider') {
      panel = {
        type: 'slider',
        dimensions: {
          x: 10,
          y: 10,
          width: 100,
          height: 100
        },
        styles: {
          color: 'white',
          background: 'blue'
        },
        action: {
          channel: 0
        },
        description: ''
      } as ISliderPanel;
    }
    if (panel != null) {
      if (!this.controlPanel.data.panels) {
        this.controlPanel.data.panels = [];
      }
      this.controlPanel.data.panels.push(panel);
    }
  }

  async toggleEditMode() {
    if (this.editMode) {
      await this.service.updateControlPanel(this.controlPanel);
      this.editMode = false;
    } else {
      this.editMode = true;
    }
  }

  editPanel(panel: IPanel, event) {
    event.stopPropagation();
    if (event.detail === 1) {
      return;
    }
    if (panel.type === 'button') {
      this.dialog.open(ButtonEditDialogComponent, {
        data: panel
      });
    } else if (panel.type === 'slider') {
      this.dialog.open(SliderEditDialogComponent, {
        data: panel
      });
    }
  }

  removePanel(panel, event) {
    if (!this.editMode || !DELETE_KEYS.includes(event.key)) {
      return;
    }
    const panels = this.controlPanel.data.panels as any[];
    const index = panels.indexOf(panel);
    panels.splice(index, 1);
  }

  move(panel: IPanel, event) {
    event.preventDefault();
    event.stopPropagation();
    if (this.dragInit == null) {
      return;
    }
    if (event.screenX === 0 && event.screenY === 0) {
      this.dragInit = null;
      return;
    }
    const rect = event.target.getBoundingClientRect();
    const dx = event.clientX - rect.left - this.dragInit[0];
    const dy = event.clientY - rect.top - this.dragInit[1];
    if (Math.abs(dx) < GRID_SIZE && Math.abs(dy) < GRID_SIZE) {
      return;
    }
    panel.dimensions.x += Math.round(dx / GRID_SIZE) * GRID_SIZE;
    panel.dimensions.y += Math.round(dy / GRID_SIZE) * GRID_SIZE;
  }

  resize(panel: IPanel, direction, event) {
    event.preventDefault();
    event.stopPropagation();
    if (event.screenX === 0 && event.screenY === 0) {
      return;
    }
    if (
      Math.abs(event.offsetX) < GRID_SIZE &&
      Math.abs(event.offsetY) < GRID_SIZE
    ) {
      return;
    }
    if (direction === 'nw') {
      const dx = Math.round(event.offsetX / GRID_SIZE) * GRID_SIZE;
      const dy = Math.round(event.offsetY / GRID_SIZE) * GRID_SIZE;
      panel.dimensions.x += dx;
      panel.dimensions.y += dy;
      panel.dimensions.width += -dx;
      panel.dimensions.height += -dy;
    } else if (direction === 'ne') {
      const dx = Math.round(event.offsetX / GRID_SIZE) * GRID_SIZE;
      const dy = Math.round(event.offsetY / GRID_SIZE) * GRID_SIZE;
      panel.dimensions.y += dy;
      panel.dimensions.width += dx;
      panel.dimensions.height += -dy;
    } else if (direction === 'sw') {
      const dx = Math.round(event.offsetX / GRID_SIZE) * GRID_SIZE;
      const dy = Math.round(event.offsetY / GRID_SIZE) * GRID_SIZE;
      panel.dimensions.x += dx;
      panel.dimensions.width += -dx;
      panel.dimensions.height += dy;
    } else if (direction === 'se') {
      panel.dimensions.width +=
        Math.round(event.offsetX / GRID_SIZE) * GRID_SIZE;
      panel.dimensions.height +=
        Math.round(event.offsetY / GRID_SIZE) * GRID_SIZE;
    }
    console.log(panel, direction, event);
  }
}
