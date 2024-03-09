import { GptVersion } from "@/app/constants";
import { useAccessStore } from "@/app/store/access";
import { MessageRole } from "@/types/chat";

const host = "http://localhost:8090";

/**
 * Header 信息import { Button, Input } from "antd";
 * import styles from "./auth.module.scss";
 *
 * import { useNavigate } from "react-router-dom";
 * import { useAccessStore } from "../../store/access";
 * import ChatGPTIcon from "../../icons/chatgpt.svg";
 * export function Auth() {
 *     const navigate = useNavigate();
 *     const access = useAccessStore();
 *     return (
 *         <div className={styles["auth-page"]}>
 *             <ChatGPTIcon/>
 *             <div className={styles["auth-title"]}>OpenAIhub</div>
 *             <div className={styles["auth-sub-title"]}>
 *                 学习AI开发、掌握AI部署、运用AI提效
 *             </div>
 *             <img
 *                 src="https://bugstack.cn/images/personal/qrcode.png"
 *                 style={{ width: 250 }}
 *             />
 *             <div className={styles["auth-tips"]}>
 *                 扫码关注公众号【bugstack虫洞栈】，
 *                 <a
 *                     href="https://bugstack.cn/images/personal/qrcode.png"
 *                     target="_blank"
 *                 >
 *                     回复【403】获取访问密码
 *                 </a>
 *             </div>
 *
 *             <Input
 *                 className={styles["auth-input"]}
 *                 type="password"
 *                 placeholder="在此处填写访问码"
 *                 value={access.accessCode}
 *                 onChange={(e) => {
 *                     access.updateCode(e.currentTarget.value);
 *                 }}
 *                 status={access.accessCodeErrorMsgs?'error': ''}
 *
 *             />
 *             {access.accessCodeErrorMsgs?<span className={styles['auth-error']}>{access.accessCodeErrorMsgs}</span>:null}
 *
 *
 *             <div className={styles["auth-actions"]}>
 *                 <Button type="primary" onClick={() => access.login()}>确认登录👣</Button>
 *                 <Button type="text">稍后再说</Button>
 *             </div>
 *             <span>
 *         说明：此平台主要以学习OpenAI为主，请合理、合法、合规的使用相关资料！
 *       </span>
 *         </div>
 *     );
 * }
 */
function getHeaders() {
    const accessState = useAccessStore.getState()

    const headers =  {
        Authorization:  accessState.token,
        'Content-Type': 'application/json;charset=utf-8'
    }

    return headers
}

/**
 * Role 角色获取接口
 */
export const getRoleList = () => {
    // 从 apiPost mock 接口获取
    // return fetch(`${host}/role/list`).then((res) =>
    //     res.json()
    // );

    // 从本地 json 文件获取
    return fetch(`/prompts.json`).then((res) => res.json());
};

/**
 * 流式应答接口
 * @param data
 */
export const completions = (data: {
    messages: {content: string; role: MessageRole}[],
    model: GptVersion
}) => {
    return fetch(`${host}/api/v1/chatgpt/chat/completions`, {
        method: 'post',
        headers: getHeaders(),
        body: JSON.stringify(data)
    });
};

/**
 * 登录鉴权接口
 * @param token
 */
export const login = (token: string) => {
    const accessState = useAccessStore.getState()
    return fetch(`${host}/api/v1/auth/login`, {
        method: 'post',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `code=${accessState.accessCode}`
    });
};
