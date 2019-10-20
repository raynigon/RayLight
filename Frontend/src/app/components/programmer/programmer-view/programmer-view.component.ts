import { Component, OnInit } from '@angular/core';
import { ICueList } from 'src/app/model/panels/ICueList';
import { CueListService } from 'src/app/services/CueListService';

@Component({
  selector: 'app-programmer-view',
  templateUrl: './programmer-view.component.html',
  styleUrls: ['./programmer-view.component.scss']
})
export class ProgrammerViewComponent implements OnInit {
  public activeCuelist: any;
  public activeCue: any;
  public activeAction: any;

  public cuelists: ICueList[];

  constructor(private cueListService: CueListService) {
    this.activeCuelist = null;
    this.activeCue = null;
    this.activeAction = null;
    this.cuelists = [];
  }

  async ngOnInit() {
    this.cuelists = await this.cueListService.getCueLists();
    if (this.activeCuelist !== null) {
      this.activeCuelist = this.cuelists.find(
        item => item.id === this.activeCuelist.id
      );
    }
  }

  async createCueList() {
    const name = prompt('Please enter a name', 'Harry Potter');
    await this.cueListService.createCueList({
      id: null,
      name,
      cues: []
    });
    this.ngOnInit();
  }

  public createCueAction() {
    if (!this.activeCue) {
      return;
    }
    this.activeCue.actions.push({
      type: 'dmx-value',
      universe: 0,
      channel: 0,
      value: 0
    });
    this.updateCue(this.activeCue);
  }

  public deleteCueAction(cue: any, action) {
    const actions = cue.actions as any[];
    const index = actions.indexOf(action);
    actions.splice(index, 1);
    this.updateCue(cue);
  }

  async updateCue(cue: any) {
    if (!this.activeCue) {
      return;
    }
    await this.cueListService.updateCue(this.activeCuelist.id, cue);
  }

  async deleteCueList(cuelist: ICueList) {
    await this.cueListService.deleteCueList(cuelist);
    this.ngOnInit();
  }

  async createCue() {
    if (!this.activeCuelist) {
      return;
    }
    const name = prompt('Please enter a name', 'Harry Potter');
    await this.cueListService.createCue(this.activeCuelist.id, {
      id: null,
      name,
      cues: []
    });
    this.ngOnInit();
  }

  async deleteCue(cue: any) {
    if (!this.activeCuelist) {
      return;
    }
    await this.cueListService.deleteCue(this.activeCuelist.id, cue.id);
    this.ngOnInit();
  }

  selectCuelist(cuelist: ICueList) {
    this.activeCuelist = cuelist;
  }

  getActiveCue(cuelist: ICueList) {
    return null;
  }
}
