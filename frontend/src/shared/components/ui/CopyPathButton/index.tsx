import React, { useState } from 'react';
import {toast} from "react-toastify";

type CopyPathButtonProps = {
    path: string;
};

export const CopyPathButton: React.FC<CopyPathButtonProps> = ({ path }) => {
    const [copied, setCopied] = useState(false);

    const handleCopy = async () => {
        try {
            await navigator.clipboard.writeText(path);
            setCopied(true);
            setTimeout(() => setCopied(false), 1500);
            toast.success('Copy to clipboard');
        } catch (err) {
            toast.error(`Copy failed: ${err}`);
        }
    };

    return (
        <div style={{ marginTop: 8 }}>
            <code style={{ background: '#eee', padding: '2px 6px', borderRadius: 4 }}>{path}</code>
            <button
                onClick={handleCopy}
                style={{
                    marginLeft: 8,
                    padding: '4px 8px',
                    border: '1px solid #ccc',
                    borderRadius: 4,
                    cursor: 'pointer',
                    background: copied ? '#d4edda' : '#f5f5f5',
                }}
            >
                {copied ? '✅ Copied!' : '📋 Copy Path'}
            </button>
        </div>
    );
};
