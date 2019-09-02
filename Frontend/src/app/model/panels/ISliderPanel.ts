import { IPanel } from './IPanel';

export interface ISliderPanel extends IPanel {
    styles: ISliderStyles;
    action: ISliderAction;
    description: string;
}

export interface ISliderStyles {
    color: string;
    background: string;
}

export interface ISliderAction {
    channel: number;
}
